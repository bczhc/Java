package s;


import pers.zhc.tools.utils.MySQLite3;

/**
 * @author bczhc
 */
public class A {
    static {
        System.loadLibrary("bczhcMain");
    }

    public static void main(String[] args) {
        MySQLite3 db = MySQLite3.open("/tmp/a.db");
        db.exec("DROP TABLE IF EXISTS a");
        db.exec("CREATE TABLE IF NOT EXISTS a(a)");
        db.exec("PRAGMA synchronous = OFF");
        db.exec("BEGIN TRANSACTION");
        long start = System.currentTimeMillis();
        int c = 0;
        while (System.currentTimeMillis() - start <= 1000) {
            db.exec("INSERT INTO a VALUES(null)");
            StringBuilder sb;
            ++c;
        }
        db.exec("COMMIT");
        System.out.println("c = " + c);
        db.close();
    }
}
