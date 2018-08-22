package pers.gengq.kyshell.os;

import org.apache.commons.io.FileUtils;

import javax.swing.text.StringContent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by gengqing on 8/22/2018
 **/
public class Repository implements Exercises {

    public static final String UTF_8 = "utf8";

    @Override
    public String getContent(int pageNumber) throws Exception {
        Path path = getPath(pageNumber);
        if (Files.exists(path)) {
            return FileUtils.readFileToString(path.toFile(), UTF_8);
        }
        return null;
    }

    public void saveContent(int pageNumber, String content) throws Exception {
        Path path = getPath(pageNumber);
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        FileUtils.write(path.toFile(), content, UTF_8, false);

    }

    private Path getPath(int pageNumber) {
        return Paths.get(".", "os", String.valueOf(pageNumber) + ".txt");
    }
}
