package filter.pre;

import cn.com.zuul.dynamicFilter.util.CustmoerPropertiesUtils;
import com.netflix.zuul.ZuulFilter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SystemPublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * Created by GL-shala on 2018/3/31.
 */
public class ServerRateLimiter extends ZuulFilter {
    //可以设置长些，防止读到运行此次系统检查时的cpu占用率，就不准了
    private static final int CPUTIME = 5000;

    private static final int PERCENT = 100;

    private static final int FAULTLENGTH = 10;
    @Autowired
    private SystemPublicMetrics systemPublicMetrics;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 800;
    }
    /** *//**
     * 读取CPU信息.
     * @param proc
     * @return
     * @author amg     * Creation date: 2008-4-25 - 下午06:10:14
     */
    private long[] readCpu(final Process proc) {
        long[] retn = new long[2];
        try {
            proc.getOutputStream().close();
            InputStreamReader ir = new InputStreamReader(proc.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line = input.readLine();
            if (line == null || line.length() < FAULTLENGTH) {
                return null;
            }
            int capidx = line.indexOf("Caption");
            int cmdidx = line.indexOf("CommandLine");
            int rocidx = line.indexOf("ReadOperationCount");
            int umtidx = line.indexOf("UserModeTime");
            int kmtidx = line.indexOf("KernelModeTime");
            int wocidx = line.indexOf("WriteOperationCount");
            long idletime = 0;
            long kneltime = 0;
            long usertime = 0;
            while ((line = input.readLine()) != null) {
                if (line.length() < wocidx) {
                    continue;
                }
                // 字段出现顺序：Caption,CommandLine,KernelModeTime,ReadOperationCount,
                // ThreadCount,UserModeTime,WriteOperation

                String caption = StringUtils.substring(line, capidx, cmdidx - 1).trim();
                String cmd = StringUtils.substring(line, cmdidx, kmtidx - 1).trim();

                if (cmd.indexOf("wmic.exe") >= 0) {
                    continue;
                }
                // log.info("line="+line);
                if (caption.equals("System Idle Process") || caption.equals("System")) {
                    idletime += Long.valueOf(StringUtils.substring(line, kmtidx, rocidx - 1).trim()).longValue();
                    idletime += Long.valueOf(StringUtils.substring(line, umtidx, wocidx - 1).trim()) .longValue();
                    continue;
                }

                kneltime += Long.valueOf(StringUtils.substring(line, kmtidx, rocidx - 1).trim()) .longValue();
                usertime += Long.valueOf(StringUtils.substring(line, umtidx, wocidx - 1).trim()) .longValue();
            }
            retn[0] = idletime;
            retn[1] = kneltime + usertime;
            return retn;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                proc.getInputStream().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public boolean shouldFilter() {


        //获取服务器参数
        Map custMap = CustmoerPropertiesUtils.custMap;
        //获取限流开关  内存 CPU使用率
        Collection<Metric<?>> metricCollection = systemPublicMetrics.metrics();
        Optional<Metric<?>> optional = metricCollection.stream().filter(t -> "mem.free".equals(t.getName())).findFirst();

        // 可使用内存
        long totalMemory = Runtime.getRuntime().totalMemory(); // kb;
        // 剩余内存
        long freeMemory = Runtime.getRuntime().freeMemory(); // kb;
        // 最大可使用内存
        long maxMemory = Runtime.getRuntime().maxMemory(); // kb;

        long freeMemory1 = optional.get().getValue().longValue();


        try {
            String procCmd = System.getenv("windir") + "//system32//wbem//wmic.exe process get Caption,CommandLine," + "KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
            // 取进程信息
            long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
            Thread.sleep(CPUTIME);
            long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
            if (c0 != null && c1 != null) {
                long idletime = c1[0] - c0[0];
                long busytime = c1[1] - c0[1];
                double v = Double.valueOf(PERCENT * (busytime) / (busytime + idletime)).doubleValue();
                return v>90;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

//        if((Boolean) custMap.get("serverPreLimitFlag") && freeMemory <(1-90/100)*(memoryUsage.getUsed()+freeMemory) ) return true;
//        return false;
    }

    @Override
    public Object run() {
        //执行限流
        return null;
    }
}
