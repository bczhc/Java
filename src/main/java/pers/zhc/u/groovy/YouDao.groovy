package pers.zhc.u.groovy


import pers.zhc.u.common.ReadIS

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class YouDao {
    static void main(String[] args) {
        def src = "a"
        def timeStamp = System.currentTimeMillis()
        def rand_int = pers.zhc.u.Random.ran_sc(0, 10)
//        def rand_int = 8
        def appVersion = "5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36"
        def bv = md5(appVersion)
        def tsStr = String.valueOf(timeStamp)
        final def str = "Nw(nmmbP%A-r6U3EUn]Aj"
        def sign = md5("fanyideskweb$src$tsStr$rand_int$str")
        def cookie = "OUTFOX_SEARCH_USER_ID=-744869165@121.227.138.203" +
                "; OUTFOX_SEARCH_USER_ID_NCOO=305645688.41944355" +
                "; _ntes_nnid=c6df9c9fddfee6150e1a3048322e76cd,$timeStamp" +
                "; DICT_UGC=be3af0da19b5c5e6aa4e17bd8d90b28a|" +
                "; JSESSIONID=abcktOOAomqZ2aW7IRkcx" +
                "; ___rl__test__cookies=$timeStamp"
        def headers = [
                ["Cookie", cookie],
                ["User-Agent", "Mozilla/$appVersion"]
        ]
        def headersLast = "Accept: application/json, text/javascript, */*; q=0.01\n" +
                "Accept-Encoding: gzip, deflate\n" +
                "Accept-Language: zh-CN,zh;q=0.9\n" +
                "Cache-Control: no-cache\n" +
                "Connection: keep-alive\n" +
                "Content-Type: application/x-www-form-urlencoded; charset=UTF-8\n" +
                "Host: fanyi.youdao.com\n" +
                "Origin: http://fanyi.youdao.com\n" +
                "Pragma: no-cache\n" +
                "Referer: http://fanyi.youdao.com/?keyfrom=dict2.index\n" +
                "X-Requested-With: XMLHttpRequest"
        def headersSplit = headersLast.split("\\n")
        for (s in headersSplit) {
            def s2 = s.split(": ")
            headers[headers.size()] = [s2[0], s2[1]]
        }
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
            def s = URLEncoder.encode(p[1], "UTF-8")
            if (sb.length() == 0) {
                sb.append("http://fanyi.youdao.com/translate_o?smartresult=dict&smartresult=rule?")
            } else sb.append('&' as char)
            sb.append(p[0]).append('=' as char).append(s)
        }
        def url = sb.toString()
        println url
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection()
        for (p in headers) {
            connection.addRequestProperty(p[0], p[1] as String)
        }
        connection.setRequestMethod("POST")
        connection.connect()
        def is = connection.inputStream
        new ReadIS(is, StandardCharsets.UTF_8).read({ s ->
            println s
        })
        connection.disconnect()
    }

    /**
     * 生成32位MD5摘要
     * @param string
     * @return
     */
    static String md5(String string) {
        if (string == null) {
            return null
        }
        char[] hexDigits = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                            'a', 'b', 'c', 'd', 'e', 'f'] as char[]
        byte[] btInput = string.getBytes()
        try {
            /** 获得MD5摘要算法的 MessageDigest 对象 */
            MessageDigest mdInst = MessageDigest.getInstance("MD5")
            /** 使用指定的字节更新摘要 */
            mdInst.update(btInput)
            /** 获得密文 */
            byte[] md = mdInst.digest()
            /** 把密文转换成十六进制的字符串形式 */
            int j = md.length
            char[] str = new char[j * 2]
            int k = 0
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]
                str[k++] = hexDigits[byte0 & 0xf]
            }
            return new String(str)
        } catch (NoSuchAlgorithmException ignored) {
            return null
        }
    }
}

class YouDaoTest {
    static void main(String[] args) {
        HttpURLConnection connection = new URL("http://127.0.0.1:8080/test.jsp?a=321").openConnection() as HttpURLConnection
        connection.setRequestProperty("a", "b")
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
        connection.setRequestProperty("User-Agent", "UA")
        connection.setRequestProperty("Cookie", "a=1;b=2;c=\"3\"")
        connection.doOutput = true
        connection.doInput = true
        connection.connect()
        def os = connection.outputStream
        os.write("a = 123".getBytes(StandardCharsets.UTF_8))
        os.flush()
        os.close()
        def is = connection.inputStream
        new ReadIS(is, "UTF-8").read({ s ->
            println s
        })
        connection.disconnect()
    }
}
