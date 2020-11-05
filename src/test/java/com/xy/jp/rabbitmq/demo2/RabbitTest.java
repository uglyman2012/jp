package com.xy.jp.rabbitmq.demo2;

import com.xy.jp.service.rabbitmqDemo.demo2.RabbitFriend;
import com.xy.jp.service.rabbitmqDemo.demo2.RabbitNickName;
import com.xy.jp.service.rabbitmqDemo.demo2.RabbitUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/10/28
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void test1() throws InterruptedException {
            RabbitFriend rabbitFriend = new RabbitFriend();
            rabbitFriend.setUserId(100000022);
            rabbitFriend.setLists(new ArrayList<>());
            rabbitFriend.getLists().add(new RabbitNickName("2637845647931","2637845647931123哈哈"));
            rabbitFriend.getLists().add(new RabbitNickName("2637","2637哈哈"));
            System.out.println("===========");
            RabbitUtils.sendModifyRemakeNameMsg(rabbitTemplate,rabbitFriend);
            System.out.println("===========");
            try {
                int read = System.in.read();
                System.out.println(read+"+++++++++++++++++++");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("end!");
        }


}
