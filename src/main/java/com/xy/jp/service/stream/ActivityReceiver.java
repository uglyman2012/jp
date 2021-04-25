package com.xy.jp.service.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/01/11
 */
@EnableBinding(ActivitySource.class)
public class ActivityReceiver {

    @StreamListener(value=ActivitySource.INPUT,condition = "headers['payload_simple_name']=='CustomerActivity'")
    public void activityAcceptMessage(Object message){
        System.out.println("message:"+message.toString());

    }
}
