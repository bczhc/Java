package pers.zhc.u.groovy.spring


import org.junit.jupiter.api.Test
import org.springframework.context.support.ClassPathXmlApplicationContext
import pers.zhc.u.Random

class Test1 {
    def emojiRange = [[0xE001, 0xE05A],
                      [0xE101, 0xE15A],
                      [0xE201, 0xE253],
                      [0xE301, 0xE34D],
                      [0xE401, 0xE44C],
                      [0xE501, 0xE537]]

    @Test
    void test() {
        println "test..."
        def xmlPath = "applicationContext.xml"
        def applicationContext = new ClassPathXmlApplicationContext(xmlPath)
        I0 i0 = applicationContext.getBean("demo1") as I0
        i0.add()
        def index = Random.ran_sc(0, 5)
        def hexArrLen = Random.ran_sc(10, 20)
        def hex = []
        for (int i = 0; i < hexArrLen; i++) {
            hex[i] = Random.ran_sc(emojiRange[index][0], emojiRange[index][1])
        }
        println "hex = $hex"
        def s = unicodeHexToString(hex as int[])
        println "s = $s"
    }

    @Test
    void test2() {
        def emojiRange = [[0x1F000, 0x1F6FF]]
    }

    synchronized unicodeHexToString(int[] code) {
        StringBuilder sb = StringBuilder.newInstance()
        for (c in code) {
            if (c > 0xFFFF && c < 0x110000) {
                sb.append((Math.floor((c - 0x10000) / 0x400) + 0xD800) as char)
                        .append(((c - 0x10000) % 0x400 + 0xDC00) as char)
            } else
                sb.append(c as char)
        }
        return sb.toString()
    }
}
