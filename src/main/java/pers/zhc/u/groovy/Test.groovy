package pers.zhc.u.groovy

import pers.zhc.u.common.ReadIS
import pers.zhc.utils.MySQLite3

import java.nio.charset.StandardCharsets

class Test {
    static void main(String[] args) {
        new File("./wubi_dict.db").delete()
        def db = MySQLite3.open("./wubi_dict.db")
        db.exec("BEGIN TRANSACTION")
        db.exec("CREATE TABLE IF NOT EXISTS wubi_dict (\n    char TEXT NOT NULL,\n    code TEXT NOT NULL,\n    num INTEGER\n)")

        def file = new File("/home/zhc/tmp/rime-wubi/wubi86.dict.txt")
        def fis = new FileInputStream(file)
        new ReadIS(fis, StandardCharsets.UTF_8).read({ s ->
            if (s.matches("^#.*\$")) return
            def split = s.split('\t')
            if (split[2].matches("[0-9]+\\.[0-9]+")) split[2] = split[2].substring(0, split[2].indexOf('.'))
            def num = split[2] as long
            if (num == 0) return
            db.exec(String.format("INSERT INTO wubi_dict VALUES('%s','%s',%d)", split[0], split[1], num))
        })

        db.exec("COMMIT")
        db.close()
        fis.close()
    }
}