package pers.zhc.u.server

import pers.zhc.u.FileU

import javax.servlet.ServletOutputStream

class F {
    synchronized f(request, response, out) {
        String abi = request.getParameter("abi")
        String fileName = request.getParameter("name")
        boolean md5 = request.getParameter("md5") != null
        fileName = fileName == null ? "" : fileName
        abi = abi == null ? "" : abi
        File d = new File(request.getServletContext().getRealPath("WEB-INF/externalJNI/libs"))
        File[] cFs = d.listFiles()
        boolean contain = false
        if (cFs != null) {
            for (File f : cFs) {
                if (f.isDirectory()) {
                    contain |= abi == f.getName()
                }
            }
            if (contain) {
                File f = new File(d, abi + File.separatorChar + fileName)
                if (f.exists() && f.isFile()) {
                    ServletOutputStream os = response.getOutputStream()
                    if (md5) {
                        String md5String = FileU.getMD5String(f)
                        os.write(md5String.getBytes())
                        os.flush()
                        os.close()
                        response.setStatus(200)
                        return
                    }
                    response.setContentType("application/octet-stream")
                    InputStream is = new FileInputStream(f)
                    FileU.StreamWrite(is, os)
                    is.close()
                    os.close()
                    response.setStatus(200)
                    System.out.println("request so lib...")
                    return
                } else {
                    out.print("not exist")
                }
            } else {
                for (File cF : cFs) {
                    out.print(cF.getName() + "<br/>")
                }
            }
        } else {
            out.print("500...")
            response.setStatus(500)
        }
        response.setStatus(204)
    }
}
