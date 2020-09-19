package pers.zhc.u.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pers.zhc.u.common.ReadIS;
import pers.zhc.u.util.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @author bczhc
 */
public class Kukuw {
    public static void main(String[] args) throws IOException {
        fetch("bczhc");
    }

    private static void fetch(String username) throws IOException {
        URL url = new URL("https://dazi.kukuw.com/list.html?where=1&s=" + username);
        final URLConnection urlConnection = Connection.get(url, null, null);
        final InputStream inputStream = urlConnection.getInputStream();
        final String html = ReadIS.readToString(inputStream, StandardCharsets.UTF_8);
        inputStream.close();
        final Document parse = Jsoup.parse(html);
        final Element pageLink = parse.getElementsByClass("page_link").get(0);
        final Element pageEnd = pageLink.getElementsByAttributeValue("title", "末页").get(0);
        final String pageEndLink = pageEnd.attr("href");
        final String format = "https:" + pageEndLink.replaceAll("[0-9]+\\.html", "%d.html");
        final int end = Integer.parseInt(pageLink.getElementsByTag("strong").get(0).text());
        final int first = end - Integer.parseInt(parse.getElementsByClass("page_info").get(0).getElementsByTag("strong").get(1).text()) + 1;
        for (int i = first; i <= end; i++) {
            final URL pageUrl = new URL(String.format(format, i));
            fetchPage(pageUrl);
        }
    }

    private static void fetchPage(URL pageUrl) throws IOException {
        final URLConnection urlConnection = Connection.get(pageUrl, null, null);
        final InputStream inputStream = urlConnection.getInputStream();
        final String html = ReadIS.readToString(inputStream, StandardCharsets.UTF_8);
        inputStream.close();
        final Document document = Jsoup.parse(html);
        final Element table = document.getElementsByClass("table").get(0);
        final Elements children = table.children();
        for (int i = 1; i < children.size(); i++) {
            final Element row = children.get(i);
            final Elements data = row.children();
            for (Element datum : data) {
                System.out.println(datum.text());
            }
        }
    }
}
