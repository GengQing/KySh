package pers.gengq.kyshell.document.format;

import org.junit.Test;
import pers.gengq.kyshell.FileUtils;

import java.io.IOException;

import static org.junit.Assert.*;

public class OneWordFormatTest {

    @Test
    public void isWord() {
        assertFalse(OneWordFormat.isWord("abcde小"));
        assertTrue(OneWordFormat.isWord("abcde"));
    }

    @Test
    public void format() throws IOException {
        OneWordFormat format = new OneWordFormat();
        String s = format.formatText(FileUtils.getString("classpath:wenku.txt"));
        System.out.println(s);
    }
}