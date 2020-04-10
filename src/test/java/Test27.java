import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import pers.zhc.u.FileU;
import pers.zhc.u.common.Documents;
import pers.zhc.u.common.ReadIS;
import pers.zhc.u.util.Connection;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public class Test27 {
    private long sum = 0;
    private String infos = "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n" +
            "Accept-Encoding: gzip, deflate\n" +
            "Accept-Language: zh-CN,zh;q=0.9\n" +
            "Cache-Control: no-cache\n" +
            "Connection: keep-alive\n" +
            "Cookie: BEC=aff2d9989b1422da3f7e45443a4e0511; ASP.NET_SessionId=dexsog24pna4ybok41xfjdu4; company=%e7%bf%9f%e7%81%bf; userid=74d256af-c5d2-40d4-b7af-9a38ea22d8e5; grade=2019; yxtToken=1eecf87dddf8ad83506ee4ab9484dff5; SERVERID=1c695556adbce01f9e51b56c53f0f392|1584543633|1584543083\n" +
            "Host: jc.iztedu.com\n" +
            "Pragma: no-cache\n" +
            "Upgrade-Insecure-Requests: 1\n" +
            "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36";
    private Map<String, String> requestPropertiesMap;
    private int N = 0;

    public Test27() {
        requestPropertiesMap = getRequestPropertyMap(this.infos, new HashMap<>());
    }

    public static Map<String, String> getRequestPropertyMap(String text, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        String[] split = text.split("\\n");
        for (String s : split) {
            String[] anInfo = s.split(": ");
            map.put(anInfo[0], anInfo[1]);
        }
        return map;
    }

    public static String getContent(URL url, @Documents.Nullable Map<String, String> queryParams
            , Map<String, String> requestProperties, boolean gZipCompressed) {
        StringBuilder sb = new StringBuilder();
        try {
            URLConnection connection = Connection.get(url, queryParams, requestProperties);
            InputStream is = connection.getInputStream();
            if (gZipCompressed) {
                return gZipUncompress(is);
            } else {
                new ReadIS(is, StandardCharsets.UTF_8).read(sb::append);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static String gZipUncompress(InputStream is) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            GZIPInputStream unGZip = new GZIPInputStream(is);
            byte[] buffer = new byte[256];
            int n;
            while ((n = unGZip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(out.toByteArray(), StandardCharsets.UTF_8);
    }

    @Test
    public void main() throws Exception {
        File file = new File("/home/zhc/code/code/java/src/test/messages.json");
        OutputStream os = new FileOutputStream(file, false);
        List<SubjectBean> subjectList = getSubjectList();
        JSONArray result = new JSONArray();
        for (SubjectBean subjectBean : subjectList) {
            JSONObject subjectsJSON = new JSONObject();
            subjectsJSON.put("subjectName", subjectBean.subjectName);
            JSONArray coursesArray = new JSONArray();
            List<OneSubjectBean> subjects = subjectBean.subjects;
            for (OneSubjectBean subject : subjects) {
                JSONObject coursesJSON = new JSONObject();
                coursesJSON.put("order", subject.order);
                coursesJSON.put("courseId", subject.pId);
                JSONArray questionsJSON = fetch(subject.pId);
                JSONArray chatJSONArray = null;
                try {
                    chatJSONArray = fetchChat(subject.pId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(subjectBean.subjectName + " " + subject.order);
                coursesJSON.put("questions", questionsJSON);
                coursesJSON.put("chatMessages", chatJSONArray);
                coursesArray.put(coursesJSON);
            }
            subjectsJSON.put("courses", coursesArray);
            result.put(subjectsJSON);
        }
        os.write(result.toString().getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();
        System.out.printf("\n共%1$d条\n", this.sum);
    }

    private JSONArray fetch(String pId) {
        try {
            JSONArray questionsArray = new JSONArray();
            URL url = new URL("http://yangshe.gensee.com/clientapi/apichannel?sc=1");
            Map<String, String> p = getRequestPropertyMap(infos, new HashMap<>());
            int pageIndex = 1;
            String fromDataStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?><qaHistory siteid=\"89391\" userid=\"1000078502\" confid=\"" + pId + "\" live=\"false\" page=\"%1$d\"/>";
            while (true) {
                URLConnection connection = Connection.post(url, String.format(fromDataStr, pageIndex), p);
                InputStream is = connection.getInputStream();
                StringBuilder sb = new StringBuilder();
                new ReadIS(is, StandardCharsets.UTF_8).read(sb::append);
                is.close();
                Document parse = Jsoup.parse(sb.toString());
                Elements questionMainTag = parse.getElementsByTag("qahistoryresponse");
                boolean more = Boolean.parseBoolean(questionMainTag.attr("more"));
                Elements children = questionMainTag.get(0).children();
                for (Element child : children) {
                    String questionOwner = child.attr("questionowner");
                    if (!questionOwner.equals("")) {
                        String question = child.attr("question");
                        String questionOwnerId = child.attr("questionownerid");
                        long timestamp = Long.parseLong(child.attr("questiontimestamp"));
                        JSONObject thisQuestion = new JSONObject();
                        thisQuestion.put("question", question)
                                .put("questionOwner", questionOwner)
                                .put("questionOwnerId", questionOwnerId)
                                .put("secondTimestamp", timestamp);
                        questionsArray.put(thisQuestion);
                        ++sum;
                    }
                }
                if (more) {
                    ++pageIndex;
                } else {
                    break;
                }
            }
            return questionsArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unused")
    private String stringToUnicode(String str) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = str.toCharArray();
        String h = "\\u";
        char a = '0';
        for (char c : charArray) {
            String s = Integer.toHexString(c);
            sb.append(h);
            int t = 4 - s.length();
            for (int i = 0; i < t; i++) sb.append(a);
            sb.append(s);
        }
        return sb.toString();
    }

    public List<SubjectBean> getSubjectList() throws IOException {
        List<SubjectElementsBean> subjectElementsBeans = new LinkedList<>();
        List<SubjectBean> subjectBeans = new LinkedList<>();
        for (int i = 0; i < 2; i++) {
            Document parse = Jsoup.parse(new File("/home/zhc/code/code/java/src/test/StudentRecord" + i + ".html"), "UTF-8");
            Element divContent = parse.getElementById("Div_Content");
            Elements divList1 = divContent.getElementsByClass("Div_List");
            Elements pList1 = divContent.getElementsByClass("P_List");
            for (int j = 0; j < divList1.size(); j++) {
                subjectElementsBeans.add(new SubjectElementsBean(divList1.get(j), pList1.get(j)));
            }
        }
        for (SubjectElementsBean subjectElementsBean : subjectElementsBeans) {
            Element div = subjectElementsBean.div;
            String title = div.getElementsByTag("h4").get(0).text();
            SubjectBean subjectBean = new SubjectBean(title);
            Elements pListChildren = subjectElementsBean.p.getElementsByTag("p");
            for (Element pListChild : pListChildren) {
                if (pListChild.text().equals("暂无点播内容")) continue;
                Matcher matcher = Pattern.compile("^[0-9]*").matcher(pListChild.text());
                if (matcher.find()) {
                    int index = Integer.parseInt(matcher.group(0));
                    String href = pListChild.getElementsByTag("a").get(0).attr("href");
                    String pId = href.substring(href.indexOf('=') + 1);
                    OneSubjectBean oneSubjectBean = new OneSubjectBean(pId, index);
                    subjectBean.subjects.add(oneSubjectBean);
                }
            }
            subjectBeans.add(subjectBean);
        }
        return subjectBeans;
    }

    @Test
    public void FindSomeone() throws IOException {
        System.out.println("enter name: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        File file = new File("/home/zhc/code/code/java/src/test/someone.json");
        OutputStream os = new FileOutputStream(file);
        com.alibaba.fastjson.JSONArray set = new com.alibaba.fastjson.JSONArray();
        InputStream is = new FileInputStream(new File("/home/zhc/code/code/java/src/test/questions.json"));
        StringBuilder sb = new StringBuilder();
        new ReadIS(is, StandardCharsets.UTF_8).read(sb::append);
        com.alibaba.fastjson.JSONArray jsonArray = com.alibaba.fastjson.JSONArray.parseArray(sb.toString());
        for (Object o : jsonArray) {
            com.alibaba.fastjson.JSONObject jsonObject = (com.alibaba.fastjson.JSONObject) o;
            com.alibaba.fastjson.JSONArray courses = jsonObject.getJSONArray("courses");
            for (Object o1 : courses) {
                com.alibaba.fastjson.JSONObject course = ((com.alibaba.fastjson.JSONObject) o1);
                com.alibaba.fastjson.JSONArray questions = course.getJSONObject("questions").getJSONArray("questions");
                for (Object o2 : questions) {
                    com.alibaba.fastjson.JSONObject question = (com.alibaba.fastjson.JSONObject) o2;
                    if (question.getString("questionOwner").equals(name)) {
                        set.add(question);
                    }
                }
            }
        }
        os.write(set.toString().getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();
        int count = set.size();
        System.out.println("count = " + count);
    }

    @Test
    public void test() throws IOException {
        resolveJSAndDownloadImages("");
    }

    private String getWidgetId() {
        return "_GS_WIDGET_" + N++ + "_" + System.currentTimeMillis();
    }

    public void resolveJSAndDownloadImages(String jsURLString) throws IOException {
        File dir = new File("/home/zhc/code/code/java/src/test/pptImage");
        if (!dir.exists()) System.out.println("dir.mkdirs() = " + dir.mkdirs());
        String pId = "qvdqCNYafa";
        String urlString = "http://yangshe.gensee.com/sdk/site/sdk/gs/tra/h5/vod";
        URL url = new URL(urlString);
        Map<String, String> params = new HashMap<>();
        params.put("widgetid", getWidgetId());
        params.put("site", "yangshe.gensee.com:80");
        params.put("ctx", "gensee_tra");
        params.put("ownerid", pId);
        params.put("code", "4241d96dd1e9030a0ac1d53a171a4dd9359cb2e2a2b6bf8d10b31dde5d52d87ef9bacc2bbbc762fd");
        params.put("bar", "false");
        params.put("uid", "1000078502");
        params.put("uname", "翟灿");
        params.put("py", "1");
        params.put("loop", "false");
        params.put("lang", "zh_CN");
        params.put("hlsmode", "false");
        params.put("gsver", "2");
        params.put("style", "height: 100%;");
        params.put("class", "gs-sdk-widget");
        params.put("jsonpcallback", "__GSJsonp.jsonpcallbac");
        Map<String, String> requestPropertyMap = getRequestPropertyMap(this.infos, new HashMap<>());
        URLConnection connection = Connection.get(url, params, requestPropertyMap);
        InputStream is = connection.getInputStream();
        final String[] xmlURLString = {null};
        new ReadIS(is, StandardCharsets.UTF_8).read(line -> {
            if (line.matches("^ *var xmlUrl=.*")) {
                int indexOf = line.indexOf('\'');
                xmlURLString[0] = line.substring(indexOf + 1, line.length() - 2);
            }
        });
        is.close();
        URL xmlURL = new URL(xmlURLString[0]);
        URLConnection xmlConnection = Connection.get(xmlURL, null, requestPropertyMap);
        InputStream xmlIS = xmlConnection.getInputStream();
        StringBuilder sb = new StringBuilder();
        new ReadIS(xmlIS, StandardCharsets.UTF_8).read(sb::append);
        Document parse = Jsoup.parse(sb.toString());
        Element main = parse.getElementsByTag("conf").get(0);
//        String id = main.attr("id");
        Elements children = main.children();
        Element documentElement = null;
        for (Element child : children) {
            if (child.attr("name").equals("document")) {
                documentElement = child;
            }
        }
        if (documentElement != null) {
            for (Element child : documentElement.children()) {
                Map<URL, File> downloadMap = new LinkedHashMap<>();
                String documentName = child.attr("name");
                File documentDir = new File(dir, documentName);
                if (!documentDir.exists()) System.out.println("documentDir.mkdir() = " + documentDir.mkdir());
                for (Element page : child.children()) {
                    int pageId = Integer.parseInt(page.attr("id"));
                    String hls = page.attr("hls");
                    URL pageImageURL = new URL(getNewURLString(xmlURLString[0], hls));
                    File imageFile = new File(documentDir, pageId + ".png");
                    downloadMap.put(pageImageURL, imageFile);
                }
                downloadImages(downloadMap, 8);
            }
        }
        xmlIS.close();
    }

    private void downloadImages(Map<URL, File> downloadMap, @SuppressWarnings("SameParameterValue") int threadNum) {
        //提前到位，做好准备；
        //专心听讲，会做笔记
        int size = downloadMap.size();
        CountDownLatch latch = new CountDownLatch(size);
        ExecutorService es = Executors.newFixedThreadPool(threadNum);
        downloadMap.forEach((url, file) -> es.execute(() -> {
            try {
                FileU.DownloadWeb(url, file);
                System.out.println(file.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            latch.countDown();
        }));
        es.shutdown();
        try {
            latch.await();
            System.out.println("All done.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Document getCode(String pId) {
        return null;
    }

    private String getNewURLString(String originalURLString, String fileName) {
        int lastIndexOf = originalURLString.lastIndexOf('/');
        return originalURLString.substring(0, lastIndexOf + 1) + fileName;
    }

    @Test
    public void test2() throws IOException {
        Document code = getCode("3H1aKkAslP");
        System.out.println(code);
    }

    @Test
    public void chatMain() {
    }

    private JSONArray fetchChat(String vodId) throws Exception {
        JSONArray chatArray = new JSONArray();
        URL vURL = new URL("http://jc.iztedu.com/UserCenter/PlayVideo.aspx?id=" + vodId);
        String frameSrc = Jsoup.parse(getContent(vURL, null, requestPropertiesMap, true)).getElementById("frame_src").attr("src");
        URL frameURL = new URL(frameSrc);
        String content = getContent(frameURL, null, requestPropertiesMap, false);
        Document document = Jsoup.parse(content);
        Elements children = document.getElementById("mainModule").getElementsByClass("bord").get(0).children();
        Map<String, String> query = new HashMap<>();
        for (Element child : children) {
            if (child.hasAttr("ownerid")) {
                Attributes attributes = child.attributes();
                for (Attribute attribute : attributes) {
                    query.put(attribute.getKey(), attribute.getValue());
                }
            }
        }
        query.put("widgetid", getWidgetId());
        query.put("class", "gs-sdk-widget");
        query.put("jsonpcallback", "__GSJsonp.jsonpcallback_0");
        String vodContent = getContent(new URL("http://yangshe.gensee.com/sdk/site/sdk/gs/tra/h5/vod"), query, requestPropertiesMap, false);
        String xmlUrl = getDeclaredStringVariable(vodContent, '\'', "xmlUrl");
        String xmlContent = null;
        xmlContent = getContent(new URL(xmlUrl), null, requestPropertiesMap, false);
        Document xmlDocument = Jsoup.parse(xmlContent);
        Element conf = xmlDocument.getElementsByTag("conf").get(0);
        String jsChat = conf.attr("jschat");
        String newURLString = getNewURLString(xmlUrl, jsChat);
        String chatJsContent = getContent(new URL(newURLString), null, requestPropertiesMap, false);
        JSONObject jsonObject = new JSONObject(chatJsContent);
        JSONArray chatJOSNArray = jsonObject.getJSONObject("module").getJSONArray("chat");
        for (Object o : chatJOSNArray) {
            JSONObject msg = new JSONObject();
            Object emsObject = ((JSONObject) o).get("ems");
            JSONArray emsArray = new JSONArray();
            if (emsObject instanceof JSONObject) {
                emsArray.put(emsObject);
            } else {
                JSONArray emsJSONArray = (JSONArray) emsObject;
                for (int i = 0; i < emsJSONArray.length(); i++) {
                    emsArray.put(emsJSONArray.getJSONObject(i));
                }
            }
            for (Object o1 : emsArray) {
                JSONObject ems = (JSONObject) o1;
                long timestamp = Long.parseLong(ems.getString("utctime"));
                String sender = ems.getString("sender");
                String senderId = ems.getString("senderId");
                String text = ems.getString("text");
                String richText = ems.getString("richtext");
                msg.put("sender", sender);
                msg.put("senderId", senderId);
                msg.put("text", text);
                msg.put("richText", richText);
                msg.put("timestamp", timestamp);
                chatArray.put(msg);
            }
        }
        return chatArray;
    }

    private String getDeclaredStringVariable(String content, @Documents.Nullable Character declareMark, String varName) {
        if (declareMark == null) {
            declareMark = '\"';
        }
        int indexOf = content.indexOf("var " + varName);
        int i1 = content.indexOf(declareMark, indexOf);
        int i2 = content.indexOf(declareMark, i1 + 1);
        return content.substring(i1 + 1, i2);
    }

    private static class OneSubjectBean {
        private String pId;
        private int order;

        private OneSubjectBean(String pId, int order) {
            this.pId = pId;
            this.order = order;
        }
    }

    private static class SubjectBean {
        private String subjectName;
        private List<OneSubjectBean> subjects;

        public SubjectBean(String subjectName) {
            subjects = new LinkedList<>();
            this.subjectName = subjectName;
        }
    }

    private static class SubjectElementsBean {
        private Element div, p;

        private SubjectElementsBean(Element div, Element p) {
            this.div = div;
            this.p = p;
        }
    }
}
