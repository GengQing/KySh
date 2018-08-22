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

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class BdExercisesTest {


    private BdExercises bdExercises;

    @Before
    public void loadProperties() throws IOException {
        File file = ResourceUtils.getFile("classpath:application.properties");
        System.getProperties().load(FileUtils.openInputStream(file));
        String url = SystemProperties.get("os.exercises");
        bdExercises = new BdExercises(url, new Repository("target"));
    }

    @Test
    public void getContent() throws Exception {
        String content = bdExercises.getContent(30);
        assertNotNull(content);
        System.out.println(content);
    }


}