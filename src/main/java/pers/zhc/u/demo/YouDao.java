package pers.zhc.u.demo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class YouDao {
    public static void main(String[] args) throws Exception {
        String from = "en";
        String to = "zh-CHS";
        String q = "Who am I? Where am I?";
        String url = "http://fanyi.youdao.com/translate_o?smartresult=dict&smartresult=rule";

        String u = "fanyideskweb";
        String d = q;
        long ctime = System.currentTimeMillis();
        String f = String.valueOf(ctime + (long) (Math.random() * 10 + 1));
        String c = "ebSeFb%=XZ%T[KZ)c(sy!";

        String sign = md5(u + d + f + c);

        Map<String, String> params = new HashMap<String, String>();
        params.put("i", q);
        params.put("from", from);
        params.put("to", to);
        params.put("smartresult", "dict");
        params.put("client", "fanyideskweb");
        params.put("salt", f);
        params.put("sign", sign);
        params.put("doctype", "json");
        params.put("version", "2.1");
        params.put("keyfrom", "fanyi.web");
        params.put("action", "FY_BY_CLICKBUTTION");
        params.put("typoResult", "false");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(getUrlWithQueryString(url, params));
//        request.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
//        request.setHeader("Accept-Encoding","gzip, deflate");
//        request.setHeader("Accept-Language","zh-CN,zh;q=0.9");
//        request.setHeader("Connection","keep-alive");
//        request.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        request.setHeader("Cookie", "OUTFOX_SEARCH_USER_ID_NCOO=1537643834.9570553; OUTFOX_SEARCH_USER_ID=1799185238@10.169.0.83; fanyi-ad-id=43155; fanyi-ad-closed=1; JSESSIONID=aaaBwRanNsqoobhgvaHmw; _ntes_nnid=07e771bc10603d984c2dc8045a293d30,1525267244050; ___rl__test__cookies=" + ctime);
//        request.setHeader("Host","fanyi.youdao.com");
//        request.setHeader("Origin","http://fanyi.youdao.com");
        request.setHeader("Referer", "http://fanyi.youdao.com/");
        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
//        request.setHeader("X-Requested-With","XMLHttpRequest");

        CloseableHttpResponse httpResponse = httpClient.execute(request);
        HttpEntity httpEntity = httpResponse.getEntity();
        String result = EntityUtils.toString(httpEntity, "UTF-8");
        EntityUtils.consume(httpEntity);    // 关闭
        httpResponse.close();

        String[] res = result.split("\"");
        StringBuilder resd = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (res[i].equals("tgt")) {
                resd.append(res[i + 2]);
            }
        }
        System.out.println(resd.toString());
    }

    private static URI getUrlWithQueryString(String url, Map<String, String> params) {
        return null;
    }

    /**
     * 生成32位MD5摘要
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (string == null) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = string.getBytes();
        try {
            /** 获得MD5摘要算法的 MessageDigest 对象 */
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            /** 使用指定的字节更新摘要 */
            mdInst.update(btInput);
            /** 获得密文 */
            byte[] md = mdInst.digest();
            /** 把密文转换成十六进制的字符串形式 */
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}