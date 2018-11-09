package pers.gengq.kyshell;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.StringJoiner;

/**
 * Created by Geng Qing on 11/9/2018
 **/
public class FileUtils {
    public static String getString(String resourceLocation) throws IOException {
        StringJoiner joiner = new StringJoiner("\n");
        File file = getFile(resourceLocation);
        Files.readAllLines(file.toPath()).forEach(joiner::add);

        return joiner.toString();
    }

    public static File getFile(String resourceLocation) throws FileNotFoundException {
        return ResourceUtils.getFile(resourceLocation);
    }
}
