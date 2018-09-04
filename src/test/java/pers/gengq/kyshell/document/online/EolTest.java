package pers.gengq.kyshell.document.online;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class EolTest {

    @Test
    public void getPage() throws IOException {
        Eol eol = new Eol();
        eol.open("http://kaoyan.eol.cn/shiti/yingyu/201605/t20160513_1397756.shtml");
        String text = eol.getPage(1);
        assertNotNull(text);
        System.out.println(text);
    }
}