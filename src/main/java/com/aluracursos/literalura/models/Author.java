package com.aluracursos.literalura.models;

import com.aluracursos.literalura.data.AuthorData;
import com.aluracursos.literalura.helpers.StringUtils;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Nullable
    private Integer birthYear;
    @Nullable
    private Integer deathYear;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;

    public Author() {
    }

    public Author(AuthorData data) {
        this.name = data.name();
        this.birthYear = data.birthYear();
        this.deathYear = data.deathYear();
    }

    @Override
    public String toString() {
        var message = """
                
                -----AUTOR-----
                Nombre: %s
                Fecha de nacimiento: %s
                Fecha de fallecimiento: %s
                Libros: %s
                ---------------
                """;

        return String.format(message,
                getName(),
                birthYear == null ? "desconocida" : birthYear,
                deathYear == null ? "desconocida" : deathYear,
                books.stream().map(Book::getTitle).toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return StringUtils.formatName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(@Nullable Integer birthYear) {
        this.birthYear = birthYear;
    }

    @Nullable
    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(@Nullable Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
