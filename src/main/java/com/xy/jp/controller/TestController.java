package com.xy.jp.controller;

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
    @RequestMapping(value = "/test")
    public String test(){
        return "sucess_hello";
    }
}
