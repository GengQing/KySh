package pers.gengq.kyshell.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Geng Qing on 11/2/2018
 **/
public interface PdfReader {


    static String read(File file) throws IOException {

        PDDocument document = PDDocument.load(file);

        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();

        return text;

    }

    static Object readImage(File file) throws IOException {
        PDDocument document = PDDocument.load(file);

        document.getPage(0);
        PDFRenderer renderer = new PDFRenderer(document);
//        BufferedImage image = renderer.renderImageWithDPI(0, 70);
        BufferedImage image = renderer.renderImageWithDPI(0, 600, ImageType.RGB);
        String pathname = "myimage.png";
        ImageIO.write(image, "PNG", new File(pathname));
        document.close();
        return pathname;




    }


}
