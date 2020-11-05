package com.xy.jp.controller;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2020/09/17
 */
public class AAA implements Cloneable {
    private BBB bbb =new BBB();

    public BBB getBbb() {
        return bbb;
    }

    public void setBbb(BBB bbb) {
        this.bbb = bbb;
    }

    public void setName(String name) {
        this.bbb.setName(name);
    }

    public String getName() {
        return this.bbb.getName();
    }


    @Override
    protected AAA clone(){
        AAA aaa = null;
        try {
            aaa = (AAA) super.clone();
            aaa.bbb  = (BBB)aaa.bbb.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return aaa;
    }

    public static void main(String[] args) {
        AAA aaa = new AAA();
        aaa.setName("张三");
        System.out.println(aaa.getName());

        AAA aaa1 = aaa.clone();
        System.out.println(aaa1.getName());

        String a="";
        aaa.setName("ppp");
        System.out.println(aaa1.getName());
    }
}


