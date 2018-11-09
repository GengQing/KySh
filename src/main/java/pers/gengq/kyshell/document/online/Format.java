package pers.gengq.kyshell.document.online;

import java.util.StringJoiner;

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

    static String join(String[] lines) {
        StringJoiner article = new StringJoiner(LINE_DELIMITER);
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            if (line != null) {
                article.add(line);
            }
        }
        return article.toString();
    }

    String formatText(String text);

}
