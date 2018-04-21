package cn.com.eureka.listener;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.netflix.config.DeploymentContext.ContextKey.serverId;


/**
 * Created by GL-shala on 2018/3/17.
 */
@Component
public class EurekaStateChangeListener {

    private static Map timeMap = new HashMap();
    /**
     * 注册监听
     * @param ere
     */
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent ere){

        InstanceInfo info = ere.getInstanceInfo();
        String appName = info.getAppName();
        System.out.println("监听服务上线："+"appName "+appName);
    }

    @EventListener
    public void listen(EurekaInstanceCanceledEvent ere){

        String serverId = ere.getServerId();
        String appName = ere.getAppName();
        Long renewtime = (Long) timeMap.get(appName);//续约时间
        Long nowtime = System.currentTimeMillis();
        Long renew2downtime = 0L;
        if(renewtime!=null) {
            renew2downtime = nowtime - renewtime;
        }
        System.out.println("监听服务下线："+"serverId "+serverId+";"+"appName "+appName+"服务宕机到提出服务时间间隔："+renew2downtime/1000);
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent ere){

        String serverId = ere.getServerId();
        String appName = ere.getAppName();
        boolean b = ere.isReplication();
        ere.setAppName(serverId+"_1");
        Long renewtime = (Long) timeMap.get(appName);//获取上次时间
        Long retime = 0L;
        Long nowtime = System.currentTimeMillis();
        timeMap.put(appName,nowtime);//保存当前时间
        if(renewtime!=null) {
            retime = nowtime - renewtime;
        }
        System.out.println("监听服务续约："+"serverId "+serverId+";"+"appName "+appName+"续约时间间隔："+retime/1000);
    }
    @EventListener
    public void listen(EurekaRegistryAvailableEvent ere){

        long starttimestamp = ere.getTimestamp();
        Object source = ere.getSource();
        System.out.println("监听注册中心启动："+"starttimestamp "+starttimestamp+";"+"source "+source);
    }
    @EventListener
    public void listen(EurekaServerStartedEvent ere){

        long starttimestamp = ere.getTimestamp();
        Object source = ere.getSource();
        System.out.println("监听服务启动："+"starttimestamp "+starttimestamp+";"+"source "+source);
    }


}
