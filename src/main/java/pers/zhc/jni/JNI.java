package pers.zhc.jni;

/**
 * @author bczhc
 */
public class JNI {
    public static class Sqlite3 {
        public interface SqliteExecCallback {
            /**
             * Callback when {@link Sqlite3#exec(long, String, SqliteExecCallback)} is called.
             *
             * @param contents content in database
             * @return whether to continue search:
             * 0: interrupt searching
             * 1: continue
             */
            int callback(String[] contents);
        }


        /**
         * Open sqlite database.
         *
         * @param path sqlite database path, if not exists, it'll create a new sqlite database
         * @return the associated id/address in JNI, and it's the "handler"
         */
        public static native long open(String path);

        /**
         * Close sqlite database
         *
         * @param id the associated id
         */
        public static native void close(long id);

        /**
         * Execute a sqlite command.
         *
         * @param id  the associated id
         * @param cmd command
         */
        public static native void exec(long id, String cmd, SqliteExecCallback callback);
    }
}
