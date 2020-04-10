package pers.zhc.u.server

import org.json.JSONObject
import z.I9License

import javax.servlet.ServletOutputStream

class E {
    synchronized f(response) {
        response.setContentType("application/json;charset=utf-8")
        JSONObject jsonObject = new JSONObject()
        String license
        try {
            license = I9License.getLicense("IDEXZRGPWE144653")
        } catch (IOException e) {
            e.printStackTrace()
            license = e.toString()
        }
        jsonObject.put("MainActivityText", System.currentTimeMillis() + "\n" + license)
        String json = jsonObject.toString()
        response.setContentLength(json.getBytes().length)
        ServletOutputStream outputStream = response.getOutputStream()
        outputStream.write(json.getBytes())
        outputStream.flush()
        outputStream.close()
    }
}
