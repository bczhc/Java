import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pers.zhc.u.FileU;
import pers.zhc.u.util.Connection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author bczhc
 */
public class Test29 {
    private final Map<String, String> requestPropertiesMap;
    private String cookie = "Hm_lvt_45d5dce7a2e8bd0a5172a0fb2abb3ff5=1585376122; captcha-key=e4065e80-a304-4b15-bdd9-4b07e1fcba05; JSESSIONID=1mb6gmc6oamx91537j1xjpcera; _sessionid4web_=1mb6gmc6oamx91537j1xjpcera; id4city=829c0d08-cad5-4b70-b661-3ffedd6e48fd; Hm_lpvt_45d5dce7a2e8bd0a5172a0fb2abb3ff5=1585377084";
    private String infos = "accept: application/json, text/plain, */*\n" +
            "accept-encoding: gzip, deflate, br\n" +
            "accept-language: zh-CN,zh;q=0.9\n" +
            "cache-control: no-cache\n" +
            "content-type: application/json;charset=utf-8\n" +
            "pragma: no-cache\n" +
            "referer: https://xs.zjgedu.cn/pagesV2/high.html\n" +
            "sec-fetch-dest: empty\n" +
            "sec-fetch-mode: cors\n" +
            "sec-fetch-site: same-origin\n" +
            "user-agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36";

    public Test29() {
        this.requestPropertiesMap = Test27.getRequestPropertyMap(infos, new HashMap<>());
        requestPropertiesMap.put("Cookie", this.cookie);
    }

    @Test
    void test() {
        fetch("ZB152000000272001026");
    }

    private void fetch(String liveCourseCode) {
        final JSONObject videoInfos = getVideoInfos(liveCourseCode);
        final JSONArray jsonArray = Objects.requireNonNull(videoInfos).getJSONObject("data").getJSONArray("liveCourseResVos");
        for (Object o : jsonArray) {
            final String resCode = ((JSONObject) o).getString("resCode");
            try {
                downloadVideo(resCode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void downloadVideo(String resCode) throws IOException {
        final URL url = new URL("https://xs.zjgedu.cn/resorg/media/" + resCode);
        Map<String, String> query = new HashMap<>();
        query.put("isAjax", "true");
        final String content = Test27.getContent(url, query, requestPropertiesMap, true);
        JSONObject jsonObject = new JSONObject(content);
        final JSONObject data = jsonObject.getJSONObject("data");
        final String resName = data.getString("resName");
        final String urlString = data.getString("downUrl");
        URL downloadURL = new URL("https://xs2.zjgedu.cn/videoView/" + urlString);
        final URLConnection connection = Connection.get(downloadURL, query, requestPropertiesMap);
        FileU.download(connection, new File("./", resName + ".mp4"), null, System.out::println);
    }

    private JSONObject getVideoInfos(String liveCourseCode) {
        try {
            final URL url = new URL("https://xs.zjgedu.cn/live/getLiveCourseResList");
            Map<String, String> query = new HashMap<>();
            query.put("isAjax", "true");
            query.put("liveCourseCode", liveCourseCode);
            final String s = Test27.getContent(url, query, this.requestPropertiesMap, true);
            return new JSONObject(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
