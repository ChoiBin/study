package com.choi.mybatis2.bean;

/**
 * @author : choibin
 * @date : 16:10  2019/12/3 0003
 */
public class Dept {

    private int did;
    private String dname;

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "did=" + did +
                ", dname='" + dname + '\'' +
                '}';
    }
}
