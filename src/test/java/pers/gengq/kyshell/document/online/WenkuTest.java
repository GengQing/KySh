package pers.gengq.kyshell.document.online;

import org.junit.Test;

import static org.junit.Assert.*;

public class WenkuTest {

    @Test
    public void getPage() {
        Wenku exercises = new Wenku("https://wenku.baidu.com/view/719c983ea66e58fafab069dc5022aaea998f41d5.html");
        String content = exercises.getPage(5);
        assertNotNull(content);
        System.out.println(content);
    }

    @Test
    public void getPageCount() {
    }
}