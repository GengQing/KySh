package pers.gengq.kyshell.pdf;

/**
 * Created by Geng Qing on 11/2/2018
 **/

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PdfToPng {


    public static void main(String[] args) {
        // 将pdf装图片 并且自定义图片得格式大小
        File file = new File("D:\\worksproj\\testpdftoimage\\20180803112140.pdf");
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
//			for (int i = 0; i < pageCount; i++) {
//				BufferedImage image = renderer.renderImageWithDPI(i, 96); // Windows
//																			// native
//																			// DPI
//				BufferedImage srcImage = resize(image, 794, 1123);// 产生缩略图
//				ImageIO.write(srcImage, "PNG", new File("D:\\worksproj\\testpdftoimage\\aaa"+i+".png"));
//			}
//			BufferedImage image = renderer.renderImageWithDPI(0, 300); // Windows
//			BufferedImage srcImage = resize(image, 2478, 3504);// 产生缩略图

            BufferedImage image = renderer.renderImageWithDPI(0, 300, ImageType.RGB);
            BufferedImage srcImage = resize(image, 1239, 1752);// 产生缩略图
            ImageIO.write(image, "PNG", new File("D:\\worksproj\\testpdftoimage\\aaa-png.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) targetW / source.getWidth();
        double sy = (double) targetH / source.getHeight();
        if (sx > sy) {
            sx = sy;
            targetW = (int) (sx * source.getWidth());
        } else {
            sy = sx;
            targetH = (int) (sy * source.getHeight());
        }
        if (type == BufferedImage.TYPE_CUSTOM) {
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else {
            target = new BufferedImage(targetW, targetH, type);
        }
        Graphics2D g = target.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }

    public static void pdfToPng(String pdffilename, String pngfilename) {
        File file = new File("D:\\worksproj\\testpdftoimage\\" + pdffilename);
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            BufferedImage image = renderer.renderImageWithDPI(0, 300, ImageType.RGB);
            ImageIO.write(image, "PNG", new File("D:\\worksproj\\testpdftoimage\\" + pngfilename));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
