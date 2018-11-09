package pers.gengq.kyshell.document.online;

import org.junit.Test;
import pers.gengq.kyshell.FileUtils;

import java.io.IOException;

import static org.junit.Assert.*;

public class WenkuFormatTest {

    @Test
    public void format() throws IOException {
        String regular = "2018";
        System.out.println(regular.length());
        assertTrue(WenkuFormat.shouldMerge(regular, ""));

        String content = FileUtils.getString("classpath:1.txt");

        String s = WenkuFormat.format3(content);
        System.out.println(s);
    }

    @Test
    public void test() throws IOException {

        String content = FileUtils.getString("classpath:1.txt");

        String s = WenkuFormat.format2(content);
        System.out.println(s);


    }


}