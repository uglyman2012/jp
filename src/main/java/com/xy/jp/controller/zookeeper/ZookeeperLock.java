package com.xy.jp.controller.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * url: https://blog.csdn.net/m0_37606374/article/details/115271100?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~aggregatepage~first_rank_ecpm_v1~rank_v31_ecpm-2-115271100-null-null.pc_agg_new_rank&utm_term=zookeeper%E5%88%A0%E9%99%A4%E7%9B%AE%E5%BD%95+%E9%9D%9E%E7%A9%BA&spm=1000.2123.3001.4430
 * </p>
 *
 * @author yang
 * @since 2022/06/05
 */
public class ZookeeperLock {

    private static final String LOCK_ROOT_PATH = "/Locks";
    private static final String LOCK_NODE_PATH = "Lock_";
    //zk的连接串
    String IP = "192.168.1.212:2181";
    //计数器对象
    CountDownLatch countDownLatch = new CountDownLatch(1);
    //zk配置信息
    private ZooKeeper zooKeeper;
    private String lockPath;
    @Autowired
    private CuratorClientUtil curatorClientUtil;
    // 在zookeeper的/rootLock节点下创建锁对应的临时有序节点
    private String rootLock = "/rootLock2";
    //监视器对象，监视上一个节点是否被删除\
    private Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            System.out.println(event.getPath() + " 前锁释放");
            if (event.getType() == Event.EventType.NodeDeleted) {
                synchronized (this) {
                    notifyAll();
                }
            }
        }
    };

    //打开zookeeper的链接
    public ZookeeperLock() throws IOException {
        try {
            zooKeeper = new ZooKeeper(IP, 10000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getType() == Event.EventType.None) {
                        if (event.getState() == Event.KeeperState.SyncConnected) {
                            System.out.println("连接创建成功~~~");
                            countDownLatch.countDown();
                        }
                    }
                }
            });
            countDownLatch.await();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void testq() throws Exception {
        curatorClientUtil.zkWatch(rootLock);
    }

    /**
     * 加锁
     *
     * @throws Exception
     */
    public void acquireLock() throws Exception {
        //创建锁节点
        createLock();
        //尝试获取锁
        attempLock();
    }

    /**
     * 创建锁节点
     *
     * @throws Exception
     */
    public void createLock() throws Exception {
        //1、打开zookeeper连接

        //2、创建Locks根节点（创建前先用exit判断是否存在，不存在创建）
        Stat stat = zooKeeper.exists(LOCK_ROOT_PATH, false);
        if (stat == null) {
            zooKeeper.create(LOCK_ROOT_PATH, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        // 3、创建临时有序节点
        lockPath = zooKeeper.create(LOCK_ROOT_PATH + "/" + LOCK_NODE_PATH, new byte[0],
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        System.out.println("创建节点成功~~~" + lockPath);
        //进行测试一下~~，看看我们的代码是否有问题~~
    }

    private void attempLock() throws Exception {

        //1、获取locks下的所有子节点
        List<String> list = zooKeeper.getChildren(LOCK_ROOT_PATH, false);

        //2、对子节点进行排序
        Collections.sort(list);

        //3、截取掉前面部分
        int index = list.indexOf(lockPath.substring(LOCK_ROOT_PATH.length() + 1));

        //4、对索引进行判断
        if (index == 0) {
            System.out.println("说明是第1位，获取锁成功！");
            return;
        } else {
            //获取上一个节点的路径
            String path = list.get(index - 1);
            //监视上一个节点
            Stat stat = zooKeeper.exists(LOCK_ROOT_PATH + "/" + path, watcher); //监视器对象，监视上一个节点是否被删除

            if (stat == null) {
                //回调，尝试重新获得锁
                attempLock();
            } else {
                //阻塞等待
                synchronized (watcher) {
                    watcher.wait();
                }
                //回调，尝试重新获得锁
                attempLock();
            }
        }
    }

    /**
     * 释放锁
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void releaseLock() throws KeeperException, InterruptedException {
        zooKeeper.delete(lockPath, -1);
        zooKeeper.close();
        System.out.println(" 锁释放：" + lockPath);
    }

}
