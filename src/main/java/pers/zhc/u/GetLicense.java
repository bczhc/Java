package pers.zhc.u;

import pers.zhc.u.util.Connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author bczhc
 */
public class GetLicense {
    public static void main(String[] args) {
        final GetLicense instance = new GetLicense();
        String licenseString = null;
        try {
            licenseString = instance.getLicenseString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(licenseString);
    }

    public String getLicenseString() throws IOException {
        URL url = new URL("http://idea.medeming.com/jihuoma/images/jihuoma.zip");
        final URLConnection urlConnection = Connection.get(url, null, null);
        final InputStream inputStream = urlConnection.getInputStream();
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipEntry entry;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            final String name = entry.getName();
            if (name.matches(".*later.*")) {
                FileU.StreamWrite(zipInputStream, baos);
            }
        }
        zipInputStream.close();
        final String licenseString = new String(baos.toByteArray());
        baos.close();
        return licenseString;
    }
}
