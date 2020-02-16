package pers.zhc.u.groovy

import pers.zhc.u.common.ReadIS

class Test {
    static void main(String[] args) {
        def url = new URL("http://bczhc.gitee.io/web/res/app/some-tools/debug/output.json")
        '''def is = url.openStream()
        def sb = StringBuilder.newInstance()
        ReadIS.newInstance(is).read({
            s -> sb.append(s).append("\n")
        })
        def jsonArray = new JSONArray(sb.toString())
        def versionCode = jsonArray.getJSONObject(0).getJSONObject("apkData").getInt("versionCode")
        println "versionCode = $versionCode"'''
        def connection = url.openConnection()
        def size = connection.contentLength
        println "size = $size"
        def is = connection.inputStream
        ReadIS.newInstance(is).read({
            s -> println(s)
        })
    }
}
