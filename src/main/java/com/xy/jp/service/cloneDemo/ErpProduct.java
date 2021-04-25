package com.xy.jp.service.cloneDemo;

import java.io.Serializable;


/**
 * @ClassName: 生产单ERP
 * @author hanwl
 * @Description: TODO
 */

public class ErpProduct implements Serializable,Cloneable{
    private static final long serialVersionUID = 1L;
    private static ErpProduct erpProduct = new ErpProduct();

    private String topicNum;//选题号 选填
    private String compName;//部件名 必填 (多个部件以 ','分隔   封面,正文)
    private String printShop;//印厂名 必填
    private String printUser; //分发人 必填
    private String reback;//是否撤回 必填  0 默认分发  1撤回分发
    private String printNum;//印数 （选填）

    public ErpProduct() {
        super();
    }

    /**
     * 调用对象创建优化
     *
     * @return
     */
    public static ErpProduct getInstance(){
        try {
            return (ErpProduct) erpProduct.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new ErpProduct();
    }

    public String getTopicNum() {
        return topicNum;
    }
    public void setTopicNum(String topicNum) {
        this.topicNum = topicNum;
    }
    public String getCompName() {
        return compName;
    }
    public void setCompName(String compName) {
        this.compName = compName;
    }
    public String getPrintShop() {
        return printShop;
    }
    public void setPrintShop(String printShop) {
        this.printShop = printShop;
    }
    public String getPrintUser() {
        return printUser;
    }
    public void setPrintUser(String printUser) {
        this.printUser = printUser;
    }
    public String getPrintNum() {
        return printNum;
    }
    public void setPrintNum(String printNum) {
        this.printNum = printNum;
    }
    public String getReback() {
        return reback;
    }
    public void setReback(String reback) {
        this.reback = reback;
    }

}
