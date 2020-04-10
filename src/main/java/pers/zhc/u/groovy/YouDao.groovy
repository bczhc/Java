package pers.zhc.u.groovy

import org.json.JSONObject
import pers.zhc.u.common.ReadIS
import pers.zhc.u.util.MD5

import java.nio.charset.StandardCharsets

class YouDao {
    static void main(String[] args) {
        def r = translate("hello.")
        println "r = $r"
    }

    static translate(str) {
        def src = str as String
        def timeStamp = System.currentTimeMillis()
        def rand_int = pers.zhc.u.Random.ran_sc(0, 10)
//        def rand_int = 8
        def appVersion = "5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36"
        def bv = MD5.md5(appVersion)
        def tsStr = String.valueOf(timeStamp)
        final def c = "Nw(nmmbP%A-r6U3EUn]Aj"
        def sign = MD5.md5("fanyideskweb$src$tsStr$rand_int$c")
        def cookie = "OUTFOX_SEARCH_USER_ID=-744869165@121.227.138.203" +
                "; OUTFOX_SEARCH_USER_ID_NCOO=305645688.41944355" +
                "; _ntes_nnid=c6df9c9fddfee6150e1a3048322e76cd,$timeStamp" +
                "; DICT_UGC=be3af0da19b5c5e6aa4e17bd8d90b28a|" +
                "; JSESSIONID=abcktOOAomqZ2aW7IRkcx" +
                "; ___rl__test__cookies=$timeStamp"
        def headers = [
                ["Cookie", cookie],
                ["User-Agent", "Mozilla/$appVersion"],
                ["Referer", "http://fanyi.youdao.com/?keyfrom=dict2.index"]
        ]
        def data = [
                ["i", src],
                ["from", "AUTO"],
                ["to", "AUTO"],
                ["smartresult", "dict"],
                ["client", "fanyideskweb"],
                ["version", "2.1"],
                ["ts", tsStr],
                ["salt", String.valueOf(timeStamp * 10 + rand_int)],
                ["doctype", "json"],
                ["keyfrom", "fanyi.web"],
                ["action", "FY_BY_REALTlME"],
                ["bv", bv],
                ["sign", sign],
        ]
        StringBuilder sb = StringBuilder.newInstance()
        for (p in data) {
            def p1 = URLEncoder.encode(p[1] as String, "UTF-8")
            if (sb.length() == 0) {
                sb.append(p[0]).append('=' as char).append(p1)
            } else
                sb.append('&' as char).append(p[0]).append('=' as char).append(p1)
        }
        def params = sb.toString()
        def url = new URL("http://fanyi.youdao.com/translate_o?smartresult=dict&smartresult=rule")
        URLConnection connection = url.openConnection()
        for (p in headers) {
            connection.setRequestProperty(p[0], p[1] as String)
        }
        connection.doInput = true
        connection.doOutput = true
        def os = connection.outputStream
        def osw = new OutputStreamWriter(os, StandardCharsets.UTF_8)
        def bw = new BufferedWriter(osw)
        bw.write(params)
        bw.flush()
        bw.close()
        osw.close()
        os.close()
        def is = connection.inputStream
        def r = StringBuilder.newInstance()
        def result
        new ReadIS(is, StandardCharsets.UTF_8).read({ s -> r.append(s) })
        JSONObject jsonObject = new JSONObject(r.toString())
        def errorCode = jsonObject.getInt("errorCode")
        if (errorCode == 0) {
            result = jsonObject.getJSONArray("translateResult").getJSONArray(0)
                    .getJSONObject(0).getString("tgt")
        } else {
            result = "error: $errorCode"
        }
        return result as String
    }
}

class YouDaoTest {
    static void main(String[] args) {
        URLConnection connection = new URL("http://127.0.0.1:8080/test.jsp?a=321").openConnection()
        connection.doOutput = true
        connection.doInput = true
        def os = connection.outputStream
        os.write("a=123".getBytes(StandardCharsets.UTF_8))
        os.flush()
        os.close()
        def is = connection.inputStream
        new ReadIS(is, "UTF-8").read({ s ->
            println s
        })
        is.close()
    }
}
