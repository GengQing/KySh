package pers.gengq.kyshell.document.online;

import org.junit.Test;

import static org.junit.Assert.*;

public class WenkuTest {

    @Test
    public void getPage() {
        Wenku wenku = Wenku.instance();
        wenku.open("https://wenku.baidu.com/view/719c983ea66e58fafab069dc5022aaea998f41d5.html");
        String content = wenku.getPage(5);
        assertNotNull(content);
        System.out.println(content);
        System.out.println(wenku.getTitle());
        wenku.open("https://wenku.baidu.com/view/ee818a0d31126edb6f1a10cc.html");
        System.out.println(wenku.getPage(1));

    }

    @Test
    public void getPageCount() {
    }
}