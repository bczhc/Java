package pers.zhc.u.server

import javax.servlet.jsp.JspWriter

class G {
    synchronized ranEmoji() {
        def emojiRange = [[0x1F600, 0x1F64F]
                          /*[0x1F680, 0x1F6FF]*/]
        def hexArrLen = ran_sc(2, 4)
        def hex = []
        for (int i = 0; i < hexArrLen; i++) {
            def index = ran_sc(0, emojiRange.size() - 1)
            hex[i] = ran_sc(emojiRange[index][0], emojiRange[index][1])
        }
        return unicodeHexToString(hex as int[])
    }

    synchronized ran_sc(int min, int max) {
        double ran_sc_db = Math.round(Math.random() * (max - min)) + min
        return (int) ran_sc_db
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

    synchronized void func(JspWriter out, a) {
        for (int i = 0; i < a; i++) {
            def e = ranEmoji()
            out.print(e)
        }
        out.println()
    }
}
