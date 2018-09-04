package pers.gengq.kyshell.document.online;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.*;

public class EolFormatTest {

    @Test
    public void format() throws IOException {
        String string = getString();
        String content = EolFormat.format(string);
        assertNotNull(content);
        System.out.println(content);
    }

    private String getString() throws IOException {
        File file = ResourceUtils.getFile("classpath:eol_read.txt");
        return FileUtils.readFileToString(file, Charset.defaultCharset());
    }

    @Test
    public void getLines() throws IOException {

        String[] lines = EolFormat.getLines(getString());
        for (String s : lines) {

            System.out.println(s);
        }
    }
}