import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import pers.zhc.u.common.ReadIS;
import pers.zhc.u.util.Connection;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Test27 {
    private String infos = "Accept: */*\n" +
            "Accept-Encoding: gzip, deflate\n" +
            "Accept-Language: zh-CN,zh;q=0.9\n" +
            "Cache-Control: no-cache\n" +
            "Connection: keep-alive\n" +
            "Content-Length: 127\n" +
            "Content-Type: application/x-www-form-urlencoded; charset=UTF-8\n" +
            "Cookie: JSESSIONID=4B88B1BA979B7E00A9B3525080CEA29A.web1; GENSEE_UUID_COOKIE=g-89391-90136447-7391-42f3-afab-6f4338774057; GENSEE_FORBIDDEN_WORDS_COOKIE_G_PC_CLIENT_ROLE_NORMAL_L5loJskMd8=1000078502; GENSEE_FORBIDDEN_WORDS_COOKIE_WEB_L5loJskMd8=1000078502\n" +
            "Host: yangshe.gensee.com\n" +
            "Origin: http://yangshe.gensee.com\n" +
            "Pragma: no-cache\n" +
            "Referer: http://yangshe.gensee.com/training/site/v/11660284?nickname=%e7%bf%9f%e7%81%bf&token=fd1dafe546c4460bf9a295c6b250b0f5&sec=md5&uid=1000078502\n" +
            "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36";
    private String fromDataStr;

    private Map<String, String> getRequestPropertyMap(Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        String[] split = infos.split("\\n");
        for (String s : split) {
            String[] anInfo = s.split(": ");
            map.put(anInfo[0], anInfo[1]);
        }
        return map;
    }

    @Test
    public void main() {
        File file = new File("/home/zhc/code/code/java/src/test/questions.json");
        OutputStream os = null;
        try {
            JSONObject resultJSON = new JSONObject(),
                    infosJSON = new JSONObject();
            os = new FileOutputStream(file, false);
            URL url = new URL("http://yangshe.gensee.com/clientapi/apichannel?sc=1");
            Map<String, String> p = getRequestPropertyMap(new HashMap<>());
            int pageIndex = 1;
            fromDataStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?><qaHistory siteid=\"89391\" userid=\"1000078502\" confid=\"Hv5uuWeVok\" live=\"false\" page=\"%1$d\"/>";
            Document fromDataParse = Jsoup.parse(String.format(fromDataStr, pageIndex));
            Element element = fromDataParse.getElementsByTag("qahistory").get(0);
            String siteId = element.attr("siteid");
            String confId = element.attr("confid");
            String userId = element.attr("userid");
            infosJSON.put("siteId", siteId)
                    .put("confId", confId)
                    .put("userId", userId);
            JSONArray questionsArray = new JSONArray();
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
                        String s = questionOwner + ": " + question;
                        System.out.println(s);
                    }
                }
                if (more) {
                    ++pageIndex;
                } else {
                    System.out.printf("本site共%1$d页", pageIndex);
                    break;
                }
            }
            resultJSON.put("infos", infosJSON).put("questions", resultJSON)
                    .put("questions", questionsArray);
            String result = resultJSON.toString();
            os.write(result.getBytes(StandardCharsets.UTF_8));
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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

    @Test
    public void test() {
        try {
            infos = "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n" +
                    "Accept-Encoding: gzip, deflate\n" +
                    "Accept-Language: zh-CN,zh;q=0.9\n" +
                    "Cache-Control: no-cache\n" +
                    "Connection: keep-alive\n" +
                    "Cookie: BEC=aff2d9989b1422da3f7e45443a4e0511; ASP.NET_SessionId=lntc2gga3hwloa5fyxcwgewn; company=%e7%bf%9f%e7%81%bf; userid=74d256af-c5d2-40d4-b7af-9a38ea22d8e5; grade=2019; SERVERID=e8550d127bb922f42ee39fd21ff03815|1583150785|1583147036\n" +
                    "Host: jc.iztedu.com\n" +
                    "Pragma: no-cache\n" +
                    "Referer: http://jc.iztedu.com/UserCenter/StudedntSchedule.aspx\n" +
                    "Upgrade-Insecure-Requests: 1\n" +
                    "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36";
            URL url = new URL("http://jc.iztedu.com/UserCenter/StudentRecord.aspx");
            Map<String, String> requestPropertyMap = getRequestPropertyMap(new HashMap<>());
            URLConnection connection = Connection.get(url, null, requestPropertyMap);
            InputStream is = connection.getInputStream();
            new ReadIS(is, StandardCharsets.UTF_8).read(line -> {
                System.out.println(line);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
