package pers.gengq.kyshell.os;

import org.junit.Test;

import static org.junit.Assert.*;

public class RepositoryTest {

    @Test
    public void getContent() {
    }

    @Test
    public void saveContent() throws Exception {
        Repository repository = new Repository("target");
        repository.saveContent(1, "hello\n world");

    }
}