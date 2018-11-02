package pers.gengq.kyshell.pdf;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class PdfReaderTest {

    @Test
    public void read() throws Exception {

        File file = ResourceUtils.getFile("classpath:c.pdf");
        String content = PdfReader.read(file);
        System.out.println(content);
        System.out.println(content.trim().length() + " is len");
        assertNotNull(content.trim());

        PdfReader.readImage(file);

    }

    @Test
    public void readImage() throws Exception {
        File file = ResourceUtils.getFile("classpath:c.pdf");
        PdfReader.readImage(file);

    }
}