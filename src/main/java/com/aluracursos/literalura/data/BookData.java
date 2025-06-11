package com.aluracursos.literalura.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        String title,
        List<String> languages,
        List<AuthorData> authors,
        List<String> summaries,
        @JsonAlias("download_count") Integer downloads
) {
}
