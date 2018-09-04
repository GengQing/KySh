package pers.gengq.kyshell.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import pers.gengq.kyshell.document.online.Eol;
import pers.gengq.kyshell.document.online.PagingOnlineDoc;
import pers.gengq.kyshell.document.online.Wenku;
import pers.gengq.kyshell.repo.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gengqing on 8/27/2018
 **/
@ShellComponent
@ShellCommandGroup("OnlineDoc")
public class PagingDocCmds {

    @Autowired
    private Environment environment;

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

    @ShellMethod(value = "list all local page num", key = "list-doc")
    public List<String> listDoc() throws IOException {
        List<String> docs = new ArrayList<>();
        System.getProperties().keySet().stream().filter(this::isDocProperty).forEach(o -> docs.add(o.toString()));
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
        } else {
            throw new RuntimeException("have no this doc");
        }
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
