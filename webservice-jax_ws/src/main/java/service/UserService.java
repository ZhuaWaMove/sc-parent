package service;

import bean.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Created by GL-shala on 2018/4/17.
 */
@WebService(name = "UserService", // 暴露服务名称
        targetNamespace = "http://service/"// 命名空间,一般是接口的包名倒序
)
public interface UserService {

    @WebMethod
    @WebResult(name = "String", targetNamespace = "")
    public String getName(@WebParam(name = "userId") Long userId);

    @WebMethod
    public User getUser(long userId);
}
