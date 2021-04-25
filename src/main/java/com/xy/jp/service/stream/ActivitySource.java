package com.xy.jp.service.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ActivitySource {
    String INPUT = "product_search_8578579";

    @Input(INPUT)
    SubscribableChannel input();
}
