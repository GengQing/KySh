package pers.gengq.kyshell.document.online;

import org.apache.commons.lang3.StringUtils;

import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Geng Qing on 10/30/2018
 **/
public class WenkuFormat implements Format {

    public static String format2(String string) {
        String[] lines = Format.getLines(string);
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            if (line.length() == 1) { // 只有一个中文字符放入上面
                boolean isadd = addToFront(lines, i);
                if (isadd) {
                    lines[i] = null;
                }
            } else if (line.length() <= 4 && isContainChinese(line)) {
                boolean isadd = addToFront(lines, i);
                if (isadd) {
                    lines[i] = null;
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

    private static boolean addToFront(String[] lines, int index) {
        for (int i = index - 1; i >= 0; i--) {
            String front = lines[i];
            if (front != null) {
                String tmp = lines[i];
                lines[i] = tmp + lines[index];
                return true;
            }
        }

        return false;
    }


    public static String format(String content) {
        StringJoiner article = new StringJoiner(LINE_DELIMITER);


        String[] lines = Format.getLines(content);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (i == lines.length - 1 || !shouldMerge(line, lines[i + 1])) { //
                article.add(line);
            } else {
//                int preLine = i - 1;
//                if (line.equals("；") || line.equals("“")) {//；“放到上一行
//                    if (preLine >= 0) {
//                        String tmp = lines[preLine] + line;
//                        lines[preLine] = tmp;
//                        continue;
//                    }
//
//                }

                int nextLine = i + 1;
                String tmp = line + lines[nextLine]; // 跟下一行合并
                lines[nextLine] = tmp; // 放入下一行

            }
        }
        return article.toString();
    }

    public static boolean shouldMerge(String line, String next) {
        if (StringUtils.isNumeric(line)) {
            return true;
        }

        if (line.length() < 5) {
            return true;
        }

        if (isContainChinese(line) && isContainChinese(next) && line.length() < 9) {
            return true;
        }

        return false;
    }

    public static boolean isContainChinese(String str) {
        if (str.contains("“") || str.contains("”") || str.contains("。")
                || str.contains("，") || str.contains("？") || str.contains("；")
                || str.contains("（") || str.contains("）")) {
            return true;
        }
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

}
