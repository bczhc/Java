package pers.zhc.u.server

import pers.zhc.u.FileU

import javax.servlet.ServletInputStream

class D {
    synchronized f(request, out) {
        File dir = new File(request.getServletContext().getRealPath("WEB-INF/tools-app-crashLog"))
        if (!dir.exists()) {
            System.out.println("dir.mkdirs() = " + dir.mkdirs())
        }
        ServletInputStream is = request.getInputStream()
        int b = is.read()
        ByteArrayOutputStream baos = new ByteArrayOutputStream()
        baos.write(b)
        baos.flush()
        if (b != -1) {
            while ((b = is.read()) != -1) {
                if (b == 0) break
                baos.write(b)
                baos.flush()
            }
            String headInformation = new String(baos.toByteArray())
            baos.close()
            File file = new File(dir, headInformation)
            System.out.println("receive crash report: " + headInformation)
            OutputStream os = new FileOutputStream(file, false)
            FileU.StreamWrite(is, os)
            os.close()
            is.close()
            System.out.println("done")
        } else {
            out.print("<h1>错误报告上传页面</h1>")
        }
    }
}
