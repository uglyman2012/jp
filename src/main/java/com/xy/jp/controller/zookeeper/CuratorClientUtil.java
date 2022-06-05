package com.xy.jp.controller.zookeeper;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2022/06/03
 */
@Slf4j
public class CuratorClientUtil {
    public static CountDownLatch watch = new CountDownLatch(1);
    private String zookeeperServer;
    @Getter
    private CuratorFramework client;


    public CuratorClientUtil(String zookeeperServer) {
        this.zookeeperServer = zookeeperServer;
    }

    // 创建CuratorFrameworkFactory并且启动
    public void init() {
        // 重试策略,等待1s,最大重试3次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        this.client = CuratorFrameworkFactory.builder()
                .connectString(zookeeperServer)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("base")
                .build();
        this.client.start();
        System.out.println("zookerper建立了了==================");
    }

    // 容器关闭,CuratorFrameworkFactory关闭
    public void destroy() {
        try {

            if (Objects.nonNull(getClient())) {
                getClient().close();
                System.out.println("zookerper销毁了==================");
            }
        } catch (Exception e) {
            log.info("CuratorFramework close error=>{}", e.getMessage());
        }
    }

    /**
     * url :https://blog.csdn.net/m0_37619396/article/details/100601053?spm=1001.2101.3001.6650.5&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-5-100601053-blog-120320950.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-5-100601053-blog-120320950.pc_relevant_default&utm_relevant_index=10
     * 注册监听
     * TreeCache: 可以将指定的路径节点作为根节点（祖先节点），对其所有的子节点操作进行监听，
     * 呈现树形目录的监听，可以设置监听深度，最大监听深度为 int 类型的最大值。
     */

    public void zkWatch(String path) throws Exception {
        TreeCache treeCache = new TreeCache(getClient(), "/rootLock2");

        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                ChildData eventData = event.getData();
                switch (event.getType()) {
                    case NODE_ADDED:
                        log.warn(path + "节点添加" + eventData.getPath() + "\t添加数据为：" + new String(eventData.getData()));
                        break;
                    case NODE_UPDATED:
                        log.warn(eventData.getPath() + "节点数据更新\t更新数据为：" + new String(eventData.getData()) + "\t版本为：" + eventData.getStat().getVersion());
                        break;
                    case NODE_REMOVED:
                        log.warn(eventData.getPath() + "节点被删除");
                        break;
                    default:
                        break;
                }
            }
        });

        treeCache.start();
        //watch.await();  //如果不执行 watch.countDown()，进程会一致阻塞在 watch.await()
    }


}
