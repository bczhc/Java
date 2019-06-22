package com.zhc.u.demo;


public class p {
    public static void main(String[] args) throws Exception {
        n n = new n();
        label:
        while (true) {
            String s = n.apiSplit(1109236592L)[1];
            switch (s) {
                case "zhcxm":
                    Runtime.getRuntime().exec("C:/Windows/system32/cmd.exe /k shutdown /h");
                    break label;
                case "zhcjs":
                    Runtime.getRuntime().exec("C:/Windows/system32/cmd.exe /c calc");
                    break label;
                case "zhcg":
                    Runtime.getRuntime().exec("C:/Windows/system32/cmd.exe /k shutdown -s -t 0");
                    break;
            }
        }
    }
}
