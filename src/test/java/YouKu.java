import org.json.JSONArray;
import org.json.JSONObject;
import pers.zhc.u.FileU;
import pers.zhc.u.ThreadSequence;
import pers.zhc.u.common.ReadIS;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class YouKu {
    public static void main(String[] args) throws Exception {
        File file = new File("/home/zhc/d/youku/downloadVideo/offlinedata/XNTUxMDQ1Nzk2/info.json");
        InputStream is = new FileInputStream(file);
        StringBuilder sb = new StringBuilder();
        new ReadIS(is, StandardCharsets.UTF_8).read(sb::append);
        is.close();
        JSONObject jsonObject = new JSONObject(sb.toString());
        ThreadSequence threadSequence = new ThreadSequence(8);
        for (int i = 0; i < 605; i++) {
            int finalI = i;
            threadSequence.execute((i1, t) -> {
                try {
                    System.out.println("正在下载" + finalI);
                    File f = new File("/home/zhc/d/youku/downloadVideo/offlinedata/webRequest/" + finalI);
                    OutputStream os = new FileOutputStream(f, false);
                    InputStream inputStream;
                    inputStream = new URL(jsonObject.getJSONArray("segInfos").getJSONObject(finalI).getString("url")).openStream();
                    FileU.StreamWrite(inputStream, os);
                    inputStream.close();
                    os.close();
                    System.out.println(finalI + "下载完成");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        threadSequence.start(() -> System.out.println("ok"));
    }

    static class Merge {
        public static void main(String[] args) throws IOException {
            OutputStream os = new FileOutputStream(new File("/home/zhc/d/youku/downloadVideo/offlinedata/webRequest/merged"), false);
            for (int i = 0; i < 605; i++) {
                File f = new File("/home/zhc/d/youku/downloadVideo/offlinedata/webRequest/" + i);
                InputStream is = new FileInputStream(f);
                FileU.StreamWrite(is, os);
                is.close();
                System.out.println(i);
            }
            os.close();
            System.out.println("ok");
        }
    }

    static class WebGet {
        public static void main(String[] args) throws Exception {
            String json = "{\"a\":[{\"seconds\":198,\"no\":0,\"size\":50575112,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/6571142C9E63F719D98DE6C50/03000B1F005AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=198&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A5c788be32b707ecdb898b29da2402e36&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":192.68,\"no\":1,\"size\":32702676,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/65715937E5A38716EE6936C46/03000B1F015AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=192&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=Ac12e7eefaf7f83ad95fd16a7820407b3&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":197.68,\"no\":2,\"size\":39083299,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/677228589A231714034482888/03000B1F025AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=197&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=Ac95ab2c7d82a0a6b22782aca76f0034f&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":191.96,\"no\":3,\"size\":38074082,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/6972F77980F3D7190415B64E0/03000B1F035AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=191&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A2fb001864017ac40569ecca1ffa4dbb5&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":197.319,\"no\":4,\"size\":30417669,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/657228585863771683AD13030/03000B1F045AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=197&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=Ac8e949e37e38528d5f1cf1d52c76c6a6&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":197.28,\"no\":5,\"size\":27137315,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/65726D63D0D3E7196ED1C6442/03000B1F055AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=197&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A764da3d39104c6f0913d5a32926a8035&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":190.319,\"no\":6,\"size\":25694391,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/67733C84D2139717592542ECD/03000B1F065AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=190&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A709093124aa3f7f48103e129998d7e59&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":196.72,\"no\":7,\"size\":28047879,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/69740BA59134171AAF0616A9D/03000B1F075AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=196&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=Aea832170a3b40d266d0c8bebad4e40bc&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":193.359,\"no\":8,\"size\":17804411,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/6773C69AA624E7201A9343604/03000B1F085AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=193&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A452dcf2889c273824a78c6c69394bf41&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":199.68,\"no\":9,\"size\":31003863,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/67740BA54124671CC4B28443F/03000B1F095AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=199&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A8ad7e38222531244f9f79c90bfb503a3&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":194,\"no\":10,\"size\":32137503,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/6974DAC672C3E7196ED1C6B85/03000B1F0A5AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=194&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=Abe2739af5c0e52257b0a7955ce7fb847&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":194.159,\"no\":11,\"size\":57301496,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/69751FD17164671CC4B2827D3/03000B1F0B5AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=194&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A7fdf89ee2a537910b3ebf848b9cfe2c5&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":194.16,\"no\":12,\"size\":45909328,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/657450B04F54A71E6FA2E3740/03000B1F0C5AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=194&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=Aeef25be8ad8386eed47b2eb1f10cb936&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":194.319,\"no\":13,\"size\":65521524,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/6975A9E741B3B7182E9D7254B/03000B1F0D5AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=194&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A65a222bef973b6e7584bbd8c0499e3ab&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":194.96,\"no\":14,\"size\":51312860,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/6975EEF24EF4C71F451B16080/03000B1F0E5AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=194&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=Aa724fa6aa8cb1c0d9482639c957b105c&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":197.16,\"no\":15,\"size\":30235390,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/6775A9E750D307139888728C6/03000B1F0F5AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=197&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A2a8f0fc66f102d743036f7285347b046&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":193.519,\"no\":16,\"size\":22134187,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/697679084404071A4449F5F46/03000B1F105AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=193&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A7be9caa16ce823c0c690a68583d265d2&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":193.159,\"no\":17,\"size\":23051770,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/677633FD71E4271B19C2227B6/03000B1F115AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=193&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=Ac314fdee2d2e155797dcff33d1b14331&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":195.679,\"no\":18,\"size\":17105405,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/6575EEF25AD4171AAF0612F60/03000B1F125AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=195&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A6b7fa362de6d53e2b731260e7d3a8291&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":195.599,\"no\":19,\"size\":23329317,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/697748299BA4771D2F6EA58C7/03000B1F135AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=195&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=Ab93f5b228ad9b9b1f071008358e52fc0&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":194.32,\"no\":20,\"size\":29007113,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/65767908F1B4171AAF0614F6D/03000B1F145AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=194&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A720a2937a7df61ddddc28d9cc2d3a8ab&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":199.439,\"no\":21,\"size\":27800011,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/6576BE135F24971E04E6D215C/03000B1F155AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=199&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=Adb312899c2ef3b7c51e139947fa9e678&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":192.44,\"no\":22,\"size\":33211711,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/67778D34E063F719D98DE3B58/03000B1F165AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=192&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A28d09ef9bc62b8220fd898f9b66e81f0&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":194.399,\"no\":23,\"size\":29851908,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/69785C55EB44871D9A2AB257D/03000B1F175AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=194&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A2a787b407a46ab17e55a9a228b03cdac&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":197.039,\"no\":24,\"size\":36831350,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/6978A160CAA317140344836D4/03000B1F185AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=197&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A9165aba1c6ea73f64d5a084de3f3888a&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":191.2,\"no\":25,\"size\":26666004,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/67785C55E084B71EDA5F03E8D/03000B1F195AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=191&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A43d3258870e4c2592c581b30a9991dff&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":196.479,\"no\":26,\"size\":28707718,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/6578174A53E3C718995993CF0/03000B1F1A5AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=196&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=Ac0a6cb48bda3bad76a216e4bb1269192&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":196.92,\"no\":27,\"size\":22679347,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/6778E66BF3233714D8BCB271C/03000B1F1B5AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=196&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A0bec7cf882cb5ee02e4c755d36993d1a&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":195.519,\"no\":28,\"size\":37098181,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/6979B58CD493F719D98DE446C/03000B1F1C5AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=195&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A6fa1307c3f38718c47cf5cf7bd062f8f&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":190.96,\"no\":29,\"size\":27278031,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/6578E66B6174071A4449F4DF7/03000B1F1D5AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=190&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A8ce661213dd2ae8c499f4d3f8ef91f4b&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"},{\"seconds\":199.56,\"no\":30,\"size\":44168754,\"type\":\"\",\"speed\":0,\"startTime\":-1,\"src\":\"http://vali.cp31.ott.cibntv.net/65792B7650B4B71EDA5F0578C/03000B1F1E5AE648CA7A1F05DEE81BA03F86D8-32CC-AE37-1011-9097911BA2DD.mp4?ccode=050F&duration=199&expire=18000&psid=5de7d7f196d4f465cd3cbfdca167dc19&ups_client_netip=75536cef&ups_ts=1573867861&ups_userid=&utid=VhubFWBmzg8CAXnkexanEX8m&vid=XNTUxMDQ1Nzk2&vkey=A4809d35c32fc644564e33e9918161ed4&s=23b3a1d2b7fc11e0a046&sp=&bc=2\",\"backup\":\"\"}]}";
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("server");
            int length = jsonArray.length();
            ThreadSequence threadSequence = new ThreadSequence(8);
            System.out.println("开始下载");
            for (int i = 0; i < length; i++) {
                int finalI = i;
                threadSequence.execute((i1, t) -> {
                    System.out.println("正在下载" + finalI);
                    try {
                        OutputStream os = new FileOutputStream("/home/zhc/d/youku/downloadVideo/offlinedata/webRequest/web_browser/" + finalI);
                        String src = jsonArray.getJSONObject(finalI).getString("src");
                        InputStream is = new URL(src).openStream();
                        FileU.StreamWrite(is, os);
                        is.close();
                        os.close();
                        System.out.println(finalI + "下载完成");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            threadSequence.start(() -> System.out.println("ok"));
        }

        static class Merge {
            public static void main(String[] args) {
                try {
                    File d = new File("/home/zhc/d/youku/downloadVideo/offlinedata/webRequest/web_browser");
                    OutputStream os = new FileOutputStream(new File(d, "merged.ts"));
                    for (int i = 0; i < 31; i++) {
                        InputStream is = new FileInputStream(new File(d, String.valueOf(i) + ".ts"));
                        FileU.StreamWrite(is, os);
                        is.close();
                        System.out.println(i);
                    }
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Skip34 {
        public static void main(String[] args) throws IOException {
            File file = new File("/home/zhc/d/youku/downloadVideo/offlinedata/new_youku/1");
            File o = new File(file.getParent() + File.separatorChar + "1_skipped");
            OutputStream os = new FileOutputStream(o, false);
            InputStream is = new FileInputStream(file);
            FileU.StreamWrite(is, os, 34);
            os.close();
            is.close();
        }
    }
}