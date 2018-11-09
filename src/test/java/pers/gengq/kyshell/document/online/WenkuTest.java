package pers.gengq.kyshell.document.online;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static org.junit.Assert.*;

public class WenkuTest {

    @Test
    public void getPage() throws IOException {
        Wenku wenku = Wenku.instance();
        wenku.open("https://wenku.baidu.com/view/674f3114f011f18583d049649b6648d7c0c70848.html");
        String originalText = wenku.getOriginalText(14);
        System.out.println(originalText);
        Files.write(Paths.get("wenku.txt"), Collections.singleton(originalText));

    }

    @Test
    public void getPageCount() {
    }
}