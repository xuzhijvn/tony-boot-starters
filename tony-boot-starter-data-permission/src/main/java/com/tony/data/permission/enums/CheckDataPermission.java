package com.tony.data.permission.enums;


/**
 * @author tony
 * @create 2021/8/30
 * @description:
 */
public enum CheckDataPermission {


    A(1, "待一审"),
    A1(11, "待一审（还需要二审）"),
    A2(12, "待一审（直接通过）"),


    B(2, "重传待一审"),
    B1(21, "重传待一审（还需要二审）"),
    B2(22, "重传待一审（直接通过）"),


    C(3, "待二审"),
    C1(31, "待二审（AI通过）"),
    C2(32, "待二审（一审通过）"),

    D(4, "重传待二审"),
    D1(41, "重传待二审（AI通过）"),
    D2(42, "重传待二审（一审通过）");

    public int code;

    public String msg;

    CheckDataPermission(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
