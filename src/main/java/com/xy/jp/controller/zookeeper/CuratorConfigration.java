package com.xy.jp.controller.zookeeper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2022/06/03
 */
@Configuration
public class CuratorConfigration {
    @Value("${yl.platform.id.zk-url}")
    private String zookeeperServer;

    // 注入时,指定initMethod和destroyMethod
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public CuratorClientUtil curatorClientUtil() {
        CuratorClientUtil clientUtil = new CuratorClientUtil(zookeeperServer);
        return clientUtil;
    }
}
