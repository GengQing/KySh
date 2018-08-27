package pers.gengq.kyshell.os;

import org.junit.Test;
import pers.gengq.kyshell.repo.Repository;

public class RepositoryTest {

    @Test
    public void getContent() {
    }

    @Test
    public void saveContent() throws Exception {
        Repository repository = new Repository();
        repository.saveContent(1, "hello\n world", "os");

    }
}