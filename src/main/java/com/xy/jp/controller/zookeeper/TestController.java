package com.xy.jp.controller.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * https://blog.csdn.net/m0_45097637/article/details/123591560
 * https://blog.csdn.net/fengxiandada/article/details/124697818?spm=1001.2014.3001.5502
 * https://blog.csdn.net/fengxiandada/article/details/124873901
 * https://blog.csdn.net/qq_28640411/article/details/118969503
 * https://blog.csdn.net/code_agent/article/details/117733586
 * </p>
 *
 * @author yang
 * @since 2022/06/03
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    // 注入client工具类
    @Autowired
    private CuratorClientUtil curatorClientUtil;
    // 在zookeeper的/rootLock节点下创建锁对应的临时有序节点
    private String rootLock = "/rootLock2";

    private int b = 0;

    @GetMapping("/testLock")
    public Object testLock() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        CuratorFramework client = curatorClientUtil.getClient();
        InterProcessMutex mutex = new InterProcessMutex(curatorClientUtil.getClient(), rootLock);

        int a = 0;
        //for (int i = 0; i <1 ; i++) {
        //    Runnable runnable = new Runnable() {
        //
        //        @SneakyThrows
        //        @Override
        //        public void run() {
        //获取当前线程的名字，方便观察那些线程在获取锁
        String threadName = Thread.currentThread().getName();

        try {
            log.info("{}---获取锁start", threadName);
            // 尝试获取锁，最长等待3s，超时放弃获取
            boolean lockFlag = mutex.acquire(3, TimeUnit.SECONDS);

            // 获取锁成功，进行业务处理
            if (lockFlag) {
                log.info("{}---获取锁success", threadName);
                // 模拟业务处理，时间为3s
                b++;
                System.out.println(b + "=================");
                Thread.sleep(3000);
            } else {
                log.info("{}---获取锁fail", threadName);
            }
        } catch (Exception e) {
            log.info("{}---获取锁异常", threadName);
        } finally {
            // 业务处理完成，释放锁，唤醒比当前线程创建的节点序号大(最靠近)的线程获取锁
            List<String> strings = client.getChildren().forPath(rootLock);


            System.out.println("所不存在" + strings.toString());

            mutex.release();


            log.info("{}---锁release", threadName);
        }
        String name = Thread.currentThread().getName();
        System.out.println(name + "b" + "num" + b);
        //        }
        //    };
        //    executorService.execute(runnable);
        //}
        //executorService.shutdown();
        return b;
    }

    @GetMapping("/testLock2")
    public Object testLock2() {

        return 6;
    }
}
