package cn.com.jdbc.dao;

import cn.com.jdbc.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by GL-shala on 2018/6/2.
 */
@Service
public class TestDao {

    @Autowired
    @Qualifier("ctldbJdbcTemplate")
    private JdbcTemplate ctldbJdbcTemplate;
    @Autowired
    @Qualifier("mngdbJdbcTemplate")
    private JdbcTemplate mngdbJdbcTemplate;

    @Transactional()
    public List getAll(String sql , Object[] param, Class T){

//        ctldbJdbcTemplate = new JdbcTemplate();
        List<Person> list = ctldbJdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Person>(Person.class));
        int i = 1/0;
        return list;

    }
    @Transactional()
    public int insertOne(String sql , Object[] param){

        int update = ctldbJdbcTemplate.update(sql, param);
//        int i = 1/0;
        return update;

    }

    public List getAllFromMngdb(String sql, Object[] o, Class<Person> personClass) {
        return mngdbJdbcTemplate.query(sql, o, new BeanPropertyRowMapper<Person>(Person.class));
    }
}