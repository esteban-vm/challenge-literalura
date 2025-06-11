package com.aluracursos.literalura.helpers;

import com.aluracursos.literalura.models.Language;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIConsumer {
    private static final String BASE_URL = "https://gutendex.com/books/";

    private static final String LANG_PARAMS = "?languages="
            + Language.SPANISH.getLanguageCode() + ","
            + Language.ENGLISH.getLanguageCode() + ","
            + Language.FRENCH.getLanguageCode() + ","
            + Language.PORTUGUESE.getLanguageCode();

    private static final String URL = BASE_URL + LANG_PARAMS + "&search=";

    public String getDataFromAPI(String bookTitle) {
        URI uri = URI.create(URL + bookTitle);
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }
}
