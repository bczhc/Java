package s;


import pers.zhc.tools.utils.MySQLite3;

import java.util.Arrays;

/**
 * @author bczhc
 */
public class A {
    static {
        System.loadLibrary("bczhcMain");
    }
    public static void main(String[] args) {
        MySQLite3 db = MySQLite3.open("/home/zhc/diary.db");
        db.exec("SELECT * FROM diary", contents -> {
            System.out.println(Arrays.toString(contents));
            return 0;
        });
        db.close();
    }
}
