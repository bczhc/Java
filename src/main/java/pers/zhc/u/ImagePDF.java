package pers.zhc.u;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

public class ImagePDF {
    private final OutputStream os;
    private Document document = null;
    private PdfWriter pdfWriter;

    public ImagePDF(OutputStream os) {
        this.os = os;
    }

    public void putImage(URL imageURL) throws IOException, DocumentException {
        Image image = Image.getInstance(imageURL);
        if (this.document == null) {
            document = new Document(new Rectangle(image.getWidth(), image.getHeight()), 0, 0, 0, 0);
            pdfWriter = PdfWriter.getInstance(document, os);
            document.open();
        }
        pdfWriter.newPage();
        document.add(image);
    }

    public void close() {
        document.close();
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}