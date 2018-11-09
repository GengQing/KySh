package pers.gengq.kyshell.document.online;

/**
 * Created by Geng Qing on 10/30/2018
 **/
public interface Format {
    int WIDTH = 100;
    String LINE_DELIMITER = "\n";
    String WORD_DELIMITER = " ";

    static String[] getLines(String content) {
        return content.split(LINE_DELIMITER);
    }

    String formatText(String text);
}
