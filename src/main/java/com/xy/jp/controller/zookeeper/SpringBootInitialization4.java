package com.xy.jp.controller.zookeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * <p>
 * url:  https://blog.csdn.net/PeterMrWang/article/details/123916482
 * url: https://blog.csdn.net/qq_39740187/article/details/124334515
 * url: https://blog.csdn.net/winerpro/article/details/125033610
 * </p>
 *
 * @author yang
 * @since 2022/06/05
 */

@Component
public class SpringBootInitialization4 implements ApplicationRunner {
    // 注入client工具类
    @Autowired
    private CuratorClientUtil curatorClientUtil;
    // 在zookeeper的/rootLock节点下创建锁对应的临时有序节点
    private String rootLock = "/rootLock2";

    //@Override
    //public void run(String... args) throws Exception {
    //    curatorClientUtil.zkWatch(rootLock);
    //    System.out.println("方式四：实现ApplicationListener<ContextRefreshedEvent>接口");
    //}

    @Override
    public void run(ApplicationArguments args) throws Exception {
        curatorClientUtil.zkWatch(rootLock);
        System.out.println("方式四：实现ApplicationListener<ContextRefreshedEvent>接口");
    }
}

