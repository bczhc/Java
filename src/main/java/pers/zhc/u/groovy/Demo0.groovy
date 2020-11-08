package pers.zhc.u.groovy

import pers.zhc.u.common.ReadIS

import java.nio.charset.StandardCharsets

class Demo0 {
    static void main(String[] args) {
        println(checkIsMe())
    }

    static checkIsMe() {
        def runtime = Runtime.getRuntime()
        def is = runtime.exec(["uname", "-a"] as String[]).getInputStream()
        def read = ReadIS.readToString(is, StandardCharsets.UTF_8)
        is.close()
        return read.matches(".*Linux zhc-ubuntu.*")
    }
}
