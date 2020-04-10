package pers.zhc.u.demo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class s {
    public static void main(String[] args) {
        String s = "1, 2, -128";
        List<Byte> l = new ArrayList<>();
        for (String s1 : s.split(",")) {
            l.add(Byte.parseByte(s1.replace(" ", "")));
        }
        System.out.println(Arrays.toString(l.toArray(new Byte[0])));
    }
}