import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.exception.BeetlException;
import org.beetl.core.exception.ErrorInfo;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GL-shala on 2019/6/2.
 */
public class BeetlDemo{

    private static  final GroupTemplate gt;


    static {
        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gt = new GroupTemplate(resourceLoader, cfg);
    }
    public static void main(String[] args) throws Exception {


        StringTest();


        queryEs();


        validateTemplate();


    }
   //简单测试
    private static void StringTest() {
        Template t = gt.getTemplate("hello,${name}");
        t.binding("name", "beetl");
        String str = t.render();
        System.out.println(str);

    }

    //查询es
    private static void queryEs() throws IOException {
        //加载模板文件
        ClasspathResourceLoader rl = new ClasspathResourceLoader("/");
        Template template = gt.getTemplate("/beet1.ftl", rl);
        Map<String, List> param = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("\"ss\"");
        list.add("\"ff\"");
        param.put("index", list);
        //绑定参数
        template.binding(param);
        String render = template.render();
        System.out.println(render);

        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://localhost:9200/_msearch");
        post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        post.addHeader("Accept-Encoding", "utf-8");
        post.addHeader("Content-Type", "application/json;charset=UTF-8");
        StringEntity entity = new StringEntity(render);
        post.setEntity(entity);

        CloseableHttpResponse execute = build.execute(post);
        HttpEntity resEntity = execute.getEntity();
        String message = EntityUtils.toString(resEntity, "utf-8");
        System.out.println(message);
    }

    //校验模板
    private static void validateTemplate() {
        //加载模板文件
        ClasspathResourceLoader rl = new ClasspathResourceLoader("/");
        //校验模板
        BeetlException ex = gt.validateTemplate("/beet1.ftl", rl);
        if(ex!=null){
            ErrorInfo error = new ErrorInfo(ex);
            int line = error.getErrorTokenLine();
            String errorToken = error.getErrorTokenText();
            String type = error.getType();
            System.out.println(line+type+errorToken);
        }
    }


}
