package pers.zhc.u.crawler;

import org.json.JSONArray;
import org.json.JSONObject;
import pers.zhc.u.common.ReadIS;
import pers.zhc.u.util.Connection;
import pers.zhc.utils.MySQLite3;

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
        String cookie = "pgv_pvi=6927831040; pgv_pvid=6595637488; ptui_loginuin=1109236592; RK=z3Akhv6GP7; ptcz=99fe8b3f306432262f5b58f4cded757f4a4d95ec77e401b35dbc2de04964c724; qz_screen=1366x768; QZ_FE_WEBP_SUPPORT=1; zzpaneluin=; zzpanelkey=; pgv_si=s1123604480; pgv_info=ssid=s818670670; uin=o1109236592; skey=@fzG6bt6sx; p_uin=o1109236592; pt4_token=GdoouMreCRiI4VtHrdYYGh*B18bc21-Yn9hSnBrYjys_; p_skey=7Y3jkmoywddWx8EVTrfBtl0feuMnVTpdjabafUVnt3k_; Loading=Yes; 1109236592_todaycount=0; 1109236592_totalcount=2133; cpu_performance_v8=17";

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
        inputStream.close();

        JSONArray resolved = resolve(read);
        MySQLite3 db = MySQLite3.open("./qq_friends_result.db");
        db.exec("DROP TABLE IF EXISTS qq_friend");
        db.exec("CREATE TABLE IF NOT EXISTS qq_friend (\n" +
                "    qq INTEGER PRIMARY KEY,\n" +
                "    name TEXT NOT NULL,\n" +
                "    remark TEXT NOT NULL,\n" +
                "    group_name TEXT NOT NULL\n" +
                ")");
        for (Object o : resolved) {
            JSONObject jsonObject = (JSONObject) o;
            String groupName = jsonObject.getString("groupName");
            JSONArray friends = jsonObject.getJSONArray("friends");
            for (Object friend : friends) {
                JSONObject friendJSONObject = (JSONObject) friend;
                long qq = friendJSONObject.getLong("qq");
                String name = friendJSONObject.getString("name");
                String remark = friendJSONObject.getString("remark");
                db.exec(String.format("INSERT INTO qq_friend VALUES(%d,'%s','%s','%s')",
                        qq,
                        name.replace("'", "''"),
                        remark.replace("'", "''"),
                        groupName.replace("'", "''")));
            }
        }
        db.close();
        System.out.println("Done.");
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

    static int getToken(String key) {
        int t = 5381;
        for (int n = 0, r = key.length(); n < r; ++n) {
            t += (t << 5) + key.charAt(n);
        }
        return t & 2147483647;
    }
}
