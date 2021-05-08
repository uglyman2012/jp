package com.xy.jp.controller;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/09/03
 */
@RestController
@RequestMapping("/demo")
public class TestController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RequestMapping(value = "/test")
    public String test(){
        return "111";
    }

    //@RequestMapping(value = "/test2")
    //public String test2()
    //{
    //    RabbitFriend rabbitFriend = new RabbitFriend();
    //    rabbitFriend.setUserId(100000022);
    //    rabbitFriend.setLists(new ArrayList<>());
    //    rabbitFriend.getLists().add(new RabbitNickName("2637845647931","2637845647931123哈哈"));
    //    rabbitFriend.getLists().add(new RabbitNickName("2637","2637哈哈"));
    //    System.out.println("===========");
    //    RabbitUtils.sendModifyRemakeNameMsg(rabbitTemplate,rabbitFriend);
    //    System.out.println("===========");
    //    try {
    //        int read = System.in.read();
    //        System.out.println(read+"+++++++++++++++++++");
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //    System.out.println("end!");
    //    return "success";
    //}


}
