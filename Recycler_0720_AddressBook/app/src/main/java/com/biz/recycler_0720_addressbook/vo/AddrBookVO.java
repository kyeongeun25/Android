package com.biz.recycler_0720_addressbook.vo;


public class AddrBookVO  {

    private int addr_image;
    private String addr_name;
    private  String addr_tel;

    public AddrBookVO() {
    }

    public AddrBookVO(int addr_image, String addr_name, String addr_tel) {
        this.addr_image = addr_image;
        this.addr_name = addr_name;
        this.addr_tel = addr_tel;
    }

    public int getAddr_image() {
        return addr_image;
    }

    public void setAddr_image(int addr_image) {
        this.addr_image = addr_image;
    }

    public String getAddr_name() {
        return addr_name;
    }

    public void setAddr_name(String addr_name) {
        this.addr_name = addr_name;
    }

    public String getAddr_tel() {
        return addr_tel;
    }

    public void setAddr_tel(String addr_tel) {
        this.addr_tel = addr_tel;
    }
}
