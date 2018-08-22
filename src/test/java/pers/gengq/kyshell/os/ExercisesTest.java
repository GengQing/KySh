package pers.gengq.kyshell.os;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.system.SystemProperties;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
public class ExercisesTest {


    private Exercises exercises;

    @Before
    public void loadProperties() throws IOException {
        File file = ResourceUtils.getFile("classpath:application.properties");
        System.getProperties().load(FileUtils.openInputStream(file));
        String url = SystemProperties.get("os.exercises");
        exercises = new Exercises(url);
    }

    @Test
    public void getExercises() throws Exception {

        String content = exercises.getExercises();
        assertNotNull(content);
        System.out.println(content);

    }

    @Test
    public void getContent() {

        exercises.getContent();
    }
}