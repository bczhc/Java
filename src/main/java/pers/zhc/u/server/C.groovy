package pers.zhc.u.server

import org.json.JSONArray

class C {
    synchronized f(request, out) {
        File dir = new File(request.getServletContext().getRealPath("WEB-INF/tools-app-crashLog"))
        if (!dir.exists()) {
            System.out.println("dir.mkdirs() = " + dir.mkdirs())
        }
        if (request.getParameter("list") != null) {
            JSONArray jsonArray = new JSONArray()
            String[] list = dir.list()
            if (list != null) {
                for (String s : list) {
                    jsonArray.put(s)
                }
            }
            out.print(jsonArray.toString())
        }
    }
}
