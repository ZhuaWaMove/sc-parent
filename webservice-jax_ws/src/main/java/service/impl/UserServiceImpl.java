package service.impl;

import bean.User;
import org.springframework.stereotype.Component;
import service.UserService;

import javax.jws.WebService;

/**
 * Created by GL-shala on 2018/4/17.
 */
@WebService(serviceName = "UserService", // 与接口中指定的name一致
        targetNamespace = "http://service/", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "service.UserService"// 接口地址
)
@Component
public class UserServiceImpl  implements UserService{
    @Override
    public String getName(Long userId) {
        return "xiaogao" + userId;
    }

    @Override
    public User getUser(long userId) {
        User user = new User();
        user.setUserId(2l);
        user.setUsername("gaoyi");
        user.setEmail("315319976@qq.com");
        return user;
    }
}
