package pers.zhc.u.groovy

import org.json.JSONObject
import pers.zhc.u.common.ReadIS

import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.regex.Pattern

class WJX {
    static void main(String[] args) {
//        def answerString = ""
        HttpURLConnection connection = new URL("http://baidu.com").openConnection() as HttpURLConnection
        connection.setRequestMethod("POST")
        setRequestProperty(connection)
        connection.connect()
        def is = connection.inputStream
        new ReadIS(is, StandardCharsets.UTF_8).read({ s ->
            println s
        })
        connection.disconnect()
    }

    static void setRequestProperty(HttpURLConnection connection) {
        def answerString = "{\"5e58a86092beb570539e59f1\":[[\"5e58a86092beb570539e59f2\"]]}"
        def data = JSONObject.newInstance()
        def pConvertData = JSONObject.newInstance()
        def date = Date.newInstance()
        def dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)
        def someParam = getSomeParam()
        data.append("total_answers_str", answerString)
        data.append("pconvert_data", pConvertData.toString())
        data.append("finish_status", "1")
        data.append("timestr", dateStr)
        data.append("idy_uuid", someParam.get("uuid") as String)

        def keySet = data.keySet()
        def size = keySet.size()
        for (int i = 0; i < size; i++) {
            def key = keySet[i]
            connection.setRequestProperty(key, data.get(key).toString())
        }
    }

    static JSONObject getSomeParam() {
        def r = JSONObject.newInstance()
        def is = new URL("https://www.wenjuan.com/s/R7Nj6vR").openStream()
        new ReadIS(is, StandardCharsets.UTF_8).read({ s ->
            if (s.matches(".*var func_name.*")) {
                def pattern = Pattern.compile("\".*\"");
                def matcher = pattern.matcher(s)
                if (matcher.find()) {
                    def t = matcher.group(0).split("\"")
                    r.append("func_name", t[1])
                }
            }
            if (s.matches(".*var rand_int.*")) {
                def split = s.split("=")
                def randInt = Integer.parseInt(split[1].substring(1, split[1].length() - 1))
                r.append("rand_int", randInt)
            }
            if (s.matches(".*var uuid.*")) {
                def matcher = Pattern.compile("\".*\"").matcher(s)
                if (matcher.find()) {
                    def split = matcher.group(0).split("\"")
                    r.append("uuid", split[1])
                }
            }
        })
        return r
    }
}

class WJX_Test {
    static void main(String[] args) {
        def r = JSONObject.newInstance()
        def is = new URL("https://www.wenjuan.com/s/R7Nj6vR").openStream()
        new ReadIS(is, StandardCharsets.UTF_8).read({ s ->
            if (s.matches(".*var project .*")) {
                def i = s.indexOf('{')
                def projectJSON = s.substring(i, s.length())
                JSONObject project = new JSONObject(projectJSON)
                def has = project.has("_id")
                def oidStr = has ? project.get("_id") : ""
                def oidJSON = new JSONObject(oidStr.toString())
                def oid = oidJSON.getString("\$oid");
                println oid
            }
        })
    }
}
