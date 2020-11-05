package com.xy.jp.rabbitmq.demo1;

import com.xy.jp.service.rabbitmqDemo.demo1.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/10/23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitmqTests {
    @Autowired
    private Sender sender;

    @Test
    public void test1(){
        sender.send();
    }
}
