package cn.com.jdbc.config;

import javax.sql.DataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * Created by GL-shala on 2018/6/2.
 */
@Configuration
public class DataSourcesConfig {

    @Bean(name = "ctldbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.ctldb")
    @Primary
    public DataSource setDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "ctldbTransactionManager")
    @Primary
    public DataSourceTransactionManager setTransactionManager(@Qualifier("ctldbDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "ctldbJdbcTemplate")
    public JdbcTemplate setctldbJdbcTemplate(@Qualifier("ctldbDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }



    @Bean(name = "mngdbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.mngdb")
    public DataSource setmngDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "mngdbTransactionManager")
    public DataSourceTransactionManager setmngTransactionManager(@Qualifier("ctldbDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mngdbJdbcTemplate")
    public JdbcTemplate setmngdbJdbcTemplate(@Qualifier("mngdbDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


    /**
     * 监控配置
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //白名单：
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny","192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername","admin2");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
