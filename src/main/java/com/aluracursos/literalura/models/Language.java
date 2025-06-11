package com.aluracursos.literalura.models;

public enum Language {
    SPANISH("es", "español"),
    ENGLISH("en", "inglés"),
    FRENCH("fr", "francés"),
    PORTUGUESE("pt", "portugués");

    private final String languageCode;
    private final String languageName;

    Language(String languageCode, String languageName) {
        this.languageCode = languageCode;
        this.languageName = languageName;
    }

    public static Language fromString(String s) {
        for (Language language : Language.values()) {
            var trimmed = s.trim();
            boolean case1 = language.languageCode.equalsIgnoreCase(trimmed);
            boolean case2 = language.languageName.equalsIgnoreCase(trimmed);

            if (case1 || case2) {
                return language;
            }
        }

        throw new IllegalArgumentException("Ningún idioma encontrado: " + s);
    }

    @Override
    public String toString() {
        return languageName;
    }

    public String getLanguageCode() {
        return languageCode;
    }
}
