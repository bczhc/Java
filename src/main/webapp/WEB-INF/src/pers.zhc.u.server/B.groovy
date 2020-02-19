package pers.zhc.u.server

import org.json.JSONException
import org.json.JSONObject
import pers.zhc.u.FileU

import javax.servlet.http.HttpServletRequest
import javax.servlet.jsp.JspWriter

class B {
    synchronized File findNewName(File f) {
        if (!f.exists()) {
            return f
        }
        String x = "." + new FileU().getFileExtension(f)
        String path = f.path
        StringBuilder fP = new StringBuilder(path.substring(0, path.lastIndexOf('.')))
        File file
        for (int i = 2; ; ++i) {
            if (fP.toString().matches("^.*\\([0-9]+\\)\$")) {
                fP = new StringBuilder(fP.substring(0, fP.length() - 3) + "(" + i + ")")
            } else fP.append(" (2)")
            if (!(file = new File(fP + x)).exists()) {
                return file
            }
        }
    }

    synchronized f1(request, response, out) {
        InputStream is = (request as HttpServletRequest).inputStream
        ByteArrayOutputStream baos = ByteArrayOutputStream.newInstance()
        int b = is.read()
        if (b == -1) {
//        out.print("\n接受multipart上传，文件头信息范围为0-127的byte，以(byte) 0结尾。\n");
            def s = "<div style=\"text-align: center; margin: 1% auto auto\">\n" +
                    "    <h1>403 Forbidden.</h1>\n" +
                    "</div>"
            (out as JspWriter).println(s)
            response.setStatus(403)
            return
        }
        baos.write(b)
        baos.flush()
        while ((b = is.read()) != -1) {
            if (b == 0) break
            baos.write(b)
            baos.flush()
        }
        String headInformation = new String(baos.toByteArray())
        baos.close()
        System.out.println("received file " + headInformation + "...")
        out.print(headInformation)
        File d = new File(request.servletContext.getRealPath("WEB-INF/upload"))
        if (!d.exists()) {
            System.out.println("d.mkdirs() = " + d.mkdirs())
        }
        String fileName = null
        try {
            JSONObject jsonObject = JSONObject.newInstance([headInformation] as Object[])
            fileName = jsonObject.getString("name")
        } catch (JSONException e) {
            e.printStackTrace()
        }
        if (fileName == null) {
            fileName = String.valueOf(System.currentTimeMillis())
        }
        File f = findNewName(new File(d.toString() + File.separatorChar + fileName))
        OutputStream os = FileOutputStream.newInstance([f, false] as Object[])
        /*int readLen;
        while ((readLen = is.read(bytes)) != -1) {
            os.write(bytes, 0, readLen);
            os.flush();
        }
        os.close();*/
        FileU.StreamWrite(is, os)
        os.close()
        is.close()
        System.out.println("done")
    }

    static void main(String... args) {
        def s = String.newInstance(["a"] as Object[])
        println "s = $s"
    }
}
