package pers.gengq.kyshell.document.online;

import java.util.StringJoiner;

/**
 * Created by gengqing on 9/4/2018
 **/
public abstract class EolFormat implements Format {

    public static String format(String content) {
        StringJoiner article = new StringJoiner(LINE_DELIMITER);
        String[] lines = Format.getLines(content);
        for (String s : lines) {
            String[] words = s.split(WORD_DELIMITER);
            StringJoiner paragraph = new StringJoiner(WORD_DELIMITER);
            int lineCnt = 1;
            for (int i = 0; i < words.length; i++) {
                paragraph.add(words[i]);
                if (paragraph.toString().length() >= WIDTH * lineCnt) {
                    paragraph.add(LINE_DELIMITER);
                    lineCnt++;
                }
            }
            paragraph.add(LINE_DELIMITER);
            article.add(paragraph.toString());
        }
        return article.toString();
    }

}
