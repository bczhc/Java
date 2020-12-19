package pers.zhc.u.server

import org.json.JSONArray
import org.json.JSONObject
import pers.zhc.u.Digest

class A {
    static void main(String[] args) {
        println(1)
    }

    synchronized String existingFileJSONString(File dir) {
        def jsonObject = JSONObject.newInstance()
        def jsonArray = JSONArray.newInstance()
        def listFiles = dir.listFiles()
        if (listFiles != null) {
            for (listFile in listFiles) {
                def child = JSONObject.newInstance()
                child.put("name", listFile.name)
                child.put("md5", Digest.getFileMd5String(listFile))
                jsonArray.put(child)
            }
            jsonObject.put("files", jsonArray)
        }
        return jsonObject.toString()
    }

    synchronized func(request, response) {
        def can = request.getParameter("can")
        def os = response.getOutputStream()
        if (can != null) {
            response.setContentLength(1)
            os.write([1] as byte[])
        } else {
            def s = existingFileJSONString(new File(request.getServletContext().getRealPath("WEB-INF/upload")))
            byte[] sBytes = s.getBytes()
            response.setContentLength(sBytes.length)
            os.write(sBytes)
            os.flush()
        }
        os.close()
    }
}
