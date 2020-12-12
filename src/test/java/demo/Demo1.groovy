package demo

import pers.zhc.u.common.ReadIS

import java.nio.charset.StandardCharsets

static void main(String[] args) {
    def url = new URL("https://hub.fastgit.org/bczhc/store/blob/master/wubi_code.db.md5?raw=true")
    def inputStream = url.openStream()
    def read = ReadIS.readToString(inputStream, StandardCharsets.US_ASCII)
    println read
}