package cn.com.jdbc.controller;

import cn.com.jdbc.entity.Person;
import cn.com.jdbc.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by GL-shala on 2018/6/2.
 */
@RestController
public class Controller {

    @Autowired
    private TestDao testDao;
    @RequestMapping("getallfromctldb")
    public List getAllPersonFromCtldb(){
        String sql = "select * from Person";

        List all = testDao.getAll(sql, null, Person.class);

        return all;
    }
    @RequestMapping("insertOneCtldb")
    public Integer insertOneCtldb(){
        String sql = "insert into person values(?,?,?)";
        Object[] param = {5,"aaa",4};
        int i = testDao.insertOne(sql, param);

        return i;
    }

    @RequestMapping("getallfrommngdb")
    public List getAllPersonFromMngdb(){
        String sql = "select * from Person";

        List all = testDao.getAllFromMngdb(sql, null, Person.class);

        return all;
    }
}
