package pers.zhc.u.crawler;

import org.json.JSONArray;
import org.json.JSONObject;
import pers.zhc.u.common.ReadIS;
import pers.zhc.u.util.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class QQFriendList {
    public static void main(String[] args) throws IOException {
        String cookie = "pgv_info=ssid=s9682334570; pgv_pvid=9881446152; pgv_pvi=770698240; pgv_si=s532400128; ptui_loginuin=1109236592; RK=O2Aswv62O5; ptcz=111bda233745ea836ab3304d8153324cf4f2707567ff20376a7efec58529e9be; midas_openid=CDFCC07D6D1FA7B66EA39169F116B092; midas_openkey=92B64EB14AD211299472693916FED4C8; uin=o1109236592; skey=@vhImZNXwU; p_uin=o1109236592; pt4_token=Zv8i*7Kv8MX5Vh-3mjzKYg5uj85IKKhplxjBV6xi5t8_; p_skey=RL9v6v5*-d20yI9pDfrJaG3rHyyq4GKL6rbE1vuyqi4_; qz_screen=1536x864; 1109236592_todaycount=0; 1109236592_totalcount=2131; QZ_FE_WEBP_SUPPORT=1; cpu_performance_v8=7; __Q_w_s_hat_seed=1";

        String header = "accept: */*\n" +
                "accept-encoding: gzip, deflate, br\n" +
                "accept-language: en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7\n" +
                "cache-control: no-cache\n" +
                "cookie: " + cookie + "\n" +
                "pragma: no-cache\n" +
                "referer: https://user.qzone.qq.com/1109236592\n" +
                "sec-fetch-dest: empty\n" +
                "sec-fetch-mode: cors\n" +
                "sec-fetch-site: same-origin\n" +
                "user-agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36";
        Map<String, String> headerMap = Connection.stringParams2Map(header);

        int g_tk = getToken(Connection.cookie2Map(headerMap.get("cookie")).get("p_skey"));

        String urlString = "https://user.qzone.qq.com/proxy/domain/r.qzone.qq.com/cgi-bin/tfriend/friend_show_qqfriends.cgi";
        Map<String, String> query = new HashMap<>();
        query.put("uin", "1109236592");
        query.put("follow_flag", "0");
        query.put("groupface_flag", "0");
        query.put("fupdate", "1");
        query.put("g_tk", String.valueOf(g_tk));
        Map<String, String> property = new HashMap<>();
        property.put("Cookie", cookie);

        URLConnection urlConnection = Connection.get(new URL(urlString), query, property);
        InputStream inputStream = urlConnection.getInputStream();
        String read = ReadIS.readToString(inputStream, StandardCharsets.UTF_8);

        JSONArray resolved = resolve(read);
        System.out.println(resolved.toString());
    }

    private static JSONArray resolve(String read) {
        JSONArray output = new JSONArray();

        read = read.substring(read.indexOf('{'), read.lastIndexOf('}') + 1);
        JSONObject json = new JSONObject(read);
        JSONObject data = json.getJSONObject("data");

        JSONArray groups = data.getJSONArray("gpnames");

        Map<Integer, List<JSONObject>> groupMap = new HashMap<>();

        JSONArray friends = data.getJSONArray("items");
        for (Object friend : friends) {
            JSONObject item = (JSONObject) friend;
            String name = item.getString("name");
            String remark = item.getString("remark");
            Integer groupId = item.getInt("groupid");
            long qq = item.getLong("uin");
            JSONObject o = new JSONObject();
            o.put("name", name);
            o.put("remark", remark);
            o.put("qq", qq);
            if (!groupMap.containsKey(groupId)) {
                groupMap.put(groupId, new LinkedList<>());
            }
            groupMap.get(groupId).add(o);
        }

        for (Object o : groups) {
            JSONObject group = (JSONObject) o;
            int id = group.getInt("gpid");
            String name = group.getString("gpname");

            JSONObject groupResult = new JSONObject();
            groupResult.put("groupName", name);
            JSONArray friendsArray = new JSONArray();
            if (groupMap.containsKey(id)) {
                for (JSONObject friendsJSONObject : groupMap.get(id)) {
                    friendsArray.put(friendsJSONObject);
                }
            }
            groupResult.put("friends", friendsArray);
            output.put(groupResult);
        }
        return output;
    }

    private static int getToken(String key) {
        int t = 5381;
        for (int n = 0, r = key.length(); n < r; ++n) {
            t += (t << 5) + key.charAt(n);
        }
        return t & 2147483647;
    }
}
