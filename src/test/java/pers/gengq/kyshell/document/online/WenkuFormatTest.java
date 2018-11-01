package pers.gengq.kyshell.document.online;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
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

        String content = getString();

        String s = WenkuFormat.format(content);
        System.out.println(s);
    }

    private String getString() throws IOException {
        StringJoiner joiner = new StringJoiner("\n");
        File file = getFile();
        Files.readAllLines(file.toPath()).forEach(joiner::add);

        return joiner.toString();
    }

    private File getFile() throws FileNotFoundException {
        return ResourceUtils.getFile("classpath:1.txt");
    }

    @Test
    public void test() throws IOException {

        String content = getString();

        String s = WenkuFormat.format2(content);
        System.out.println(s);


    }


}