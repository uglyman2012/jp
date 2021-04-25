package com.xy.jp.service.ifelse;

import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/12/01
 */
@Service
public class WeixinPay implements IPay {


    @Override
    public boolean support(String code) {
        return "weixin".equals(code);
    }

    @Override
    public void pay() {
        System.out.println("===发起微信支付===");
    }
}
