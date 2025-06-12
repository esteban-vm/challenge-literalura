package com.aluracursos.literalura.helpers;

import java.net.URLEncoder;
import java.nio.charset.Charset;

public class StringUtils {
    public static String encodeTitle(String title) {
        return URLEncoder.encode(title.trim(), Charset.defaultCharset());
    }


    public static String formatName(String originalName) {
        if (!originalName.contains(",")) return originalName;

        String[] parts = originalName.split(",", 2);
        String lastnames = parts[0].trim();
        String firstnames = parts[1].trim();

        return firstnames + " " + lastnames;
    }


    public static String formatSummary(String originalSummary, int wordsPerLine) {
        if (originalSummary == null || originalSummary.isEmpty() || wordsPerLine <= 0) {
            return originalSummary;
        }

        String[] words = originalSummary.split("\\s+");
        StringBuilder result = new StringBuilder();
        int wordCounter = 0;

        for (String word : words) {
            result.append(word);
            wordCounter++;

            if (word.matches(".*[.;?]$")) {
                result.append("\n");
                wordCounter = 0;
            } else if (wordCounter >= wordsPerLine) {
                result.append("\n");
                wordCounter = 0;
            } else {
                result.append(" ");
            }
        }

        return result.toString().trim();
    }
}
