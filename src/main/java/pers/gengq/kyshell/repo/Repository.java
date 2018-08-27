package pers.gengq.kyshell.repo;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gengqing on 8/22/2018
 **/
@Component
public class Repository {

    public static final String CHARSET = "utf8";

    private String repoDir;

    public Repository() {
        String repoDir = System.getProperty("repoDir");
        if (repoDir != null) {
            this.repoDir = repoDir;
        } else {
            String userHome = System.getProperty("user.home");
            this.repoDir = userHome + File.separator + "kyshell_repo";
        }
    }


    public String getContent(int pageNumber, String name) throws Exception {
        Path path = getPath(pageNumber, name);
        if (Files.exists(path)) {
            return FileUtils.readFileToString(path.toFile(), CHARSET);
        }
        return null;
    }

    public void saveContent(int pageNumber, String content, String name) throws Exception {
        Path path = getPath(pageNumber, name);
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        FileUtils.write(path.toFile(), content, CHARSET, false);

    }

    private Path getPath(int pageNumber, String name) {
        return Paths.get(repoDir, name, String.valueOf(pageNumber) + ".txt");
    }

    public List<String> findAll(String name) throws IOException {
        Path path = Paths.get(repoDir, name);
        return Files.walk(path).filter(Files::isRegularFile)
                .map(path1 -> path1.getFileName().toString())
                .collect(Collectors.toList());
    }


}
