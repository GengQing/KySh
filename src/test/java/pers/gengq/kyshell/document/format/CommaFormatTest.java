package pers.gengq.kyshell.document.format;

import org.junit.Test;
import pers.gengq.kyshell.FileUtils;

import java.io.IOException;

import static org.junit.Assert.*;

public class CommaFormatTest {

    @Test
    public void format() throws IOException {
        String s = FileUtils.getString("classpath:wenku.txt");
        CommaFormat commaFormat = new CommaFormat();
        String result = commaFormat.formatText(s);
        System.out.println(result);

    }
}