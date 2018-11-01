package pers.gengq.kyshell.document.online;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.StringJoiner;

import static org.junit.Assert.*;

public class WenkuFormatTest {

    @Test
    public void format() throws IOException {
        String regular = "2018";
        System.out.println(regular.length());
        assertTrue(WenkuFormat.shouldMerge(regular, ""));

        File file = ResourceUtils.getFile("classpath:1.txt");
        StringJoiner joiner = new StringJoiner("\n");
        Files.readAllLines(file.toPath()).forEach(joiner::add);

        String s = WenkuFormat.format(joiner.toString());
        System.out.println(s);
    }


}