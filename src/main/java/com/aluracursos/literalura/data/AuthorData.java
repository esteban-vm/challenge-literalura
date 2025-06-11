package com.aluracursos.literalura.data;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AuthorData(
        @JsonAlias("birth_year") Integer birthYear,
        @JsonAlias("death_year") Integer deathYear,
        String name
) {
}
