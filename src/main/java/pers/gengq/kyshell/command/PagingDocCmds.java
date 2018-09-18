package pers.gengq.kyshell.command;

import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.PropertyAccessorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import pers.gengq.kyshell.document.online.Cnedu;
import pers.gengq.kyshell.document.online.Eol;
import pers.gengq.kyshell.document.online.PagingOnlineDoc;
import pers.gengq.kyshell.document.online.Wenku;
import pers.gengq.kyshell.repo.Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Created by gengqing on 8/27/2018
 **/
@ShellComponent
@ShellCommandGroup("OnlineDoc")
public class PagingDocCmds {

    @Autowired
    private AbstractEnvironment environment;

    @Autowired
    private Repository repository;


    @ShellMethod(value = "get the page of the doc", key = "get-doc")
    public String getPage(@ShellOption(value = "Name", help = "the name of doc") String name,
                          @ShellOption("PageNumber") int pageNumber) throws Exception {
        String content = repository.getContent(pageNumber, name);
        if (content == null) {
            PagingOnlineDoc doc = pagingOnlineDoc(name);
            doc.open(getUrl(name));
            content = doc.getPage(pageNumber);
            repository.saveContent(pageNumber, content, name);
        }
        return content;
    }

    @ShellMethod(value = "list all local page num", key = "list-no")
    public List<String> listLocalPageNumber(@ShellOption(value = "Name", help = "the name of the doc")
                                                    String name) throws IOException {
        return repository.findAll(name);
    }

    @ShellMethod(value = "list doc names", key = "list-doc")
    public List<String> listDoc() throws IOException {
        List<String> docs = new ArrayList<>();
        Properties properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
        FileInputStream inputStream = new FileInputStream(Paths.get("./application.properties").toFile());
        properties.load(inputStream);
        inputStream.close();
        properties.keySet().stream().filter(this::isDocProperty).forEach(o -> docs.add(o.toString()));
        return docs;
    }


    private String getUrl(String name) {
        return environment.getProperty(name);
    }

    private PagingOnlineDoc pagingOnlineDoc(String name) {
        if (isWenku(name)) {
            return Wenku.instance();
        } else if (isEol(name)) {
            return new Eol();
        } else if (isCnedu(name)) {
            return new Cnedu();
        } else {
            throw new RuntimeException("have no this doc");
        }
    }

    private boolean isCnedu(String name) {
        return name.startsWith("cnedu");
    }

    private boolean isEol(String name) {
        return name.startsWith("eol");
    }

    private boolean isWenku(String name) {
        return name.startsWith("wenku");
    }

    private boolean isDocProperty(Object key) {
        String s = key.toString();
        return isDocProperty(s);
    }

    private boolean isDocProperty(String key) {
        return isEol(key) || isWenku(key);
    }
}
