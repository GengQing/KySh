package pers.gengq.kyshell.document.format;

import pers.gengq.kyshell.document.online.Format;

/**
 * Created by Geng Qing on 11/9/2018
 **/
public class OneWordFormat implements Format {


    @Override
    public String formatText(String text) {
        String[] lines = Format.getLines(text);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line == null) {
                continue;
            }
            if (isWord(line)) {
                addwords(lines, i);
            }
        }

        return Format.join(lines);
    }

    private void addwords(String[] lines, int i) {
        String sentence = lines[i];
        for (int j = i + 1; j < lines.length; j++) {
            String next = lines[j];
            if (isWord(next)) {
                sentence = sentence + " " + next;
                lines[j] = null;
            } else {
                break;
            }
        }
        lines[i] = sentence;
    }


    static boolean isWord(String s) {
        return s.matches("[a-zA-Z]+");
    }
}
