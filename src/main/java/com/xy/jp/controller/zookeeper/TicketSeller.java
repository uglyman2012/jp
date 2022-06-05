package com.xy.jp.controller.zookeeper;

/**
 * <p>
 * 原文链接
 * url="https://blog.csdn.net/qq_40726820/article/details/117744168"
 * </p>
 *
 * @author yang
 * @since 2022/06/05
 */
public class TicketSeller {
    public static void main(String[] args) throws Exception {
        TicketSeller ticketSeller = new TicketSeller();
        for (int i = 0; i < 1000; i++) {
            ticketSeller.sellTicketWithLock();

        }
    }

    private void sell() {
        System.out.println("售票开始");
        // 线程随机休眠数毫秒，模拟现实中的费时操作
        int sleepMillis = (int) (Math.random() * 2000);
        try {
            //代表复杂逻辑执行了一段时间
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("售票结束");
    }

    public void sellTicketWithLock() throws Exception {
        ZookeeperLock lock = new ZookeeperLock();
        lock.acquireLock();
        sell();
        lock.releaseLock();
    }
}
