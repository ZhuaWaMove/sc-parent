package test.ws;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import service.UserService;
import test.ws.service.UserService_Service;

/**
 * Created by GL-shala on 2018/4/24.
 */
public class WsClientTest {

    public static void main(String[] args) {

        /* //客户端代码
        UserService_Service uss = new UserService_Service();
        UserService userService = uss.getUserServiceImplPort();
        String name = userService.getName(111L);
        System.out.println(name);*/

        //动态代理
        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = clientFactory.createClient("http://localhost:8881/soap/user?wsdl");
        try {
            Object[] objects = client.invoke("getName", 111L);
            System.out.println(objects[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
