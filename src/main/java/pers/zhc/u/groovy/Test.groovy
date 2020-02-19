package pers.zhc.u.groovy

import pers.zhc.u.server.G

class Test {
    static void main(String[] args) {
        G g = G.newInstance()
        StringBuilder sb = StringBuilder.newInstance()
        for (int i = 0; i < 1000; i++) {
            sb.append(g.ranEmoji())
        }
        def s = sb.toString()
        println "s = $s"
    }
}