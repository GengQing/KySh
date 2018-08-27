package pers.gengq.kyshell.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import pers.gengq.kyshell.document.online.Wenku;
import pers.gengq.kyshell.repo.Repository;

/**
 * Created by gengqing on 8/27/2018
 **/
@ShellComponent
@ShellCommandGroup("WENKU")
public class WenkuCommands {

    @Autowired
    private Environment environment;

    @Autowired
    private Repository repository;

    private Wenku wenku;

    public Wenku getWenku() {
        if (wenku == null) {
            wenku = new Wenku();
        }
        return wenku;
    }

    @ShellMethod(value = "get baidu-wenku", key = "Get-Wenku")
    public String getPage(@ShellOption(value = "Name", help = "the name associate to a url, config in properties") String name,
                          @ShellOption("PageNumber") int pageNumber) throws Exception {

        String content = repository.getContent(pageNumber, name);
        if (content != null) {
            return content;
        } else {
            Wenku wenku = getWenku();
            wenku.open(getUrl(name));
            content = wenku.getPage(pageNumber);
            repository.saveContent(pageNumber, content, name);
        }
        return content;
    }

    private String getUrl(String name) {
        return environment.getProperty("wenku." + name);
    }
}
