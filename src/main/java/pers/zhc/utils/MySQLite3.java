package pers.zhc.utils;

import pers.zhc.jni.JNI;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author bczhc
 */
public class MySQLite3 {
    static {
        System.load("/usr/lib/x86_64-linux-gnu/libbczhcMain.so");
    }

    private long id;
    private boolean isClosed = false;

    private MySQLite3() {
    }

    public static MySQLite3 open(String path) {
        final MySQLite3 db = new MySQLite3();
        db.id = JNI.Sqlite3.open(path);
        return db;
    }

    public void close() {
        JNI.Sqlite3.close(this.id);
        isClosed = true;
    }

    public void exec(String cmd, JNI.Sqlite3.SqliteExecCallback callback) {
        JNI.Sqlite3.exec(this.id, cmd, callback);
    }

    public void exec(String cmd) {
        exec(cmd, null);
    }

    public boolean isClosed() {
        return isClosed;
    }

    public boolean hasTable(String tableName) {
        AtomicBoolean r = new AtomicBoolean(false);
        try {
            exec("SELECT name FROM sqlite_master WHERE type='table' AND name='" + tableName + "';", contents -> {
                r.set(true);
                return 1;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r.get();
    }
}