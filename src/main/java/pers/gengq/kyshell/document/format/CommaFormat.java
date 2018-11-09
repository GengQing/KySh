package pers.gengq.kyshell.document.format;

import pers.gengq.kyshell.document.online.Format;

import java.util.StringJoiner;

/**
 * Created by Geng Qing on 11/9/2018
 **/
public class CommaFormat implements Format {

    @Override
    public String formatText(String text) {

        String s = text;
        for (int i = 0; i < 3; i++) {
            if (needFormat(s)) {
                s = formatOne(s);
            }
        }
        return s;
    }

    private boolean needFormat(String text) {
        String[] lines = Format.getLines(text);
        for (String line : lines) {
            if (endWithJoiner(line)) {
                return true;
            }
        }
        return false;
    }

    private String formatOne(String text) {
        String[] lines = Format.getLines(text);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line == null) {
                continue;
            }
            if (endWithJoiner(line)) { //，
                if (i + 1 < lines.length) {
                    lines[i] = addNextLine(lines, i);
                    lines[i + 1] = null;
                }
            }
        }

        StringJoiner article = new StringJoiner(LINE_DELIMITER);
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            if (line != null) {
                article.add(line);
            }
        }
        return article.toString();
    }

    private boolean endWithJoiner(String line) {
        return line.endsWith("，") || line.endsWith("、") || line.endsWith("：");
    }

    String addNextLine(String[] lines, int i) {
        if (lines[i + 1] != null) {
            return lines[i] + lines[i + 1];
        }
        return lines[i];
    }
}
