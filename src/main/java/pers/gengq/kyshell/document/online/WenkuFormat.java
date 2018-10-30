package pers.gengq.kyshell.document.online;

import org.apache.commons.lang3.StringUtils;

import java.util.StringJoiner;

/**
 * Created by Geng Qing on 10/30/2018
 **/
public class WenkuFormat implements Format {


    public static String format(String content) {
        StringJoiner article = new StringJoiner(LINE_DELIMITER);


        String[] lines = Format.getLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (!shouldMerge(line)) { //
                article.add(line);
            } else {
                int nextLine = i + 1;
                String tmp = line + lines[nextLine]; // 跟下一行合并
                lines[nextLine] = tmp; // 放入下一行
            }
        }
        return article.toString();
    }

    public static boolean shouldMerge(String line) {
        if (StringUtils.isNumeric(line)) {
            return true;
        }
        return line.length() > 4;
    }

}
