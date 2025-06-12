package com.aluracursos.literalura.helpers;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;

public class TextTranslator {
    public static String translate(String text) {
        try (Client client = new Client()) {
            GenerateContentConfig config = GenerateContentConfig
                    .builder()
                    .maxOutputTokens(2000)
                    .temperature(0.7f)
                    .build();

            GenerateContentResponse response =
                    client.models.generateContent(
                            "gemini-2.0-flash",
                            "traduce a español el siguiente texto: " + text,
                            config);

            return response.text();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
