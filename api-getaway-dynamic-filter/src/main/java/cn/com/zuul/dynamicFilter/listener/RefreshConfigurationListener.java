package cn.com.zuul.dynamicFilter.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.Set;

/**
 * Created by GL-shala on 2018/3/29.
 */
@Component
public class RefreshConfigurationListener implements ApplicationListener<RefreshRemoteApplicationEvent> {

    private static Log log = LogFactory.getLog(RefreshConfigurationListener.class);
    private ContextRefresher contextRefresher;

    public RefreshConfigurationListener(ContextRefresher contextRefresher) {
        this.contextRefresher = contextRefresher;
    }

    @Override
    public void onApplicationEvent(RefreshRemoteApplicationEvent refreshRemoteApplicationEvent) {
        Set<String> keys = this.contextRefresher.refresh();
        log.info("Customer Configuration Refresh Received remote refresh request. Keys refreshed " + keys);
        //取出更新的key
    }
}
