package pers.gengq.kyshell.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import pers.gengq.kyshell.os.Exercises;
import pers.gengq.kyshell.repo.Repository;

import java.io.IOException;
import java.util.List;

/**
 * Created by gengqing on 8/22/2018
 **/
@ShellComponent
@ShellCommandGroup("OS")
public class OsCommands {


    @Autowired
    private Repository repository;

    private Exercises exercises;

    @Autowired
    public OsCommands(Exercises exercises) {
        this.exercises = exercises;
    }

    @ShellMethod(value = "get exercises of OS", key = "Get-Exercises")
    public String getExercises(@ShellOption(defaultValue = "1") int pageNumber,
                               @ShellOption(defaultValue = "GB2312") String charset) throws Exception {
        String content = exercises.getContent(pageNumber);
        return new String(content.getBytes(), charset);
    }

    @ShellMethod(value = "list all", key = "find-All")
    public List<String> getAllExercises() throws IOException {
        return repository.findAll();
    }
}
