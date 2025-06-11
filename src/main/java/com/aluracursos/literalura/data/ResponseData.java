package com.aluracursos.literalura.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseData(
        Integer count,
        List<BookData> results
) {
}
