package com.xy.jp.service.ifelse;

public interface IPay {
    boolean support(String code);

    void pay();
}
