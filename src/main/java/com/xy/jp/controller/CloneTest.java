package com.xy.jp.controller;

import com.xy.jp.service.cloneDemo.ErpProduct;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/11/20
 */
public class CloneTest {
    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();

        //for (int i = 0; i < 100000; i++) {
        //    ErpProduct.getInstance();
        //}

        for (int i = 0; i < 100000; i++) {
            new ErpProduct();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("采用new的方法，一共花费的时间：" + (endTime - beginTime));
    }
}
