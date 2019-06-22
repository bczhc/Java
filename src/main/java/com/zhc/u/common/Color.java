package com.zhc.u.common;


public class Color {
    public String ranColor() {
        return "#" + Integer.toHexString(com.zhc.u.Random.ran_sc(0, 0xFF))
                + Integer.toHexString(com.zhc.u.Random.ran_sc(0, 0xFF))
                + Integer.toHexString(com.zhc.u.Random.ran_sc(0, 0xFF));
    }
}