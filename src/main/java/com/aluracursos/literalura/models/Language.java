package com.aluracursos.literalura.models;

public enum Language {
    SPANISH("es", "español", "\uD83C\uDDEA\uD83C\uDDF8"),
    ENGLISH("en", "inglés", "\uD83C\uDDEC\uD83C\uDDE7"),
    FRENCH("fr", "francés", "\uD83C\uDDEB\uD83C\uDDF7"),
    PORTUGUESE("pt", "portugués", "\uD83C\uDDF5\uD83C\uDDF9");

    private final String languageCode;
    private final String languageName;
    private final String countryFlag;

    Language(String languageCode, String languageName, String countryFlag) {
        this.languageCode = languageCode;
        this.languageName = languageName;
        this.countryFlag = countryFlag;
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

    public String getCountryFlag() {
        return countryFlag;
    }
}
