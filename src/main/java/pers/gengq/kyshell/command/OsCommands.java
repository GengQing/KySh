package pers.gengq.kyshell.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import pers.gengq.kyshell.os.Exercises;

/**
 * Created by gengqing on 8/22/2018
 **/
@ShellComponent
public class OsCommands {


    private Exercises exercises;

    @Autowired
    public OsCommands(Exercises exercises) {
        this.exercises = exercises;
    }

    @ShellMethod(value = "get exercises of OS", key = "getEx")
    public String getExercises(@ShellOption(defaultValue = "1") int pageNumber) throws Exception {
        return exercises.getContent(pageNumber);
    }
}
