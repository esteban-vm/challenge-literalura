package com.aluracursos.literalura.helpers;

import java.net.URLEncoder;
import java.nio.charset.Charset;

public class StringUtils {
    public static String formatName(String originalName) {
        if (!originalName.contains(",")) return originalName;

        String[] parts = originalName.split(",", 2);
        String lastnames = parts[0].trim();
        String firstnames = parts[1].trim();

        return firstnames + " " + lastnames;
    }

    public static String encodeTitle(String title) {
        return URLEncoder.encode(title.trim(), Charset.defaultCharset());
    }
}
