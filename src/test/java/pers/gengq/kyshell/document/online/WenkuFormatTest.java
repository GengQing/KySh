package pers.gengq.kyshell.document.online;

import org.junit.Test;

import static org.junit.Assert.*;

public class WenkuFormatTest {

    @Test
    public void format() {
        String regular = "2018";
        System.out.println(regular.length());
        assertTrue(WenkuFormat.shouldMerge(regular));
    }


}