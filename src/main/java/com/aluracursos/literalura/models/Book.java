package com.aluracursos.literalura.models;

import com.aluracursos.literalura.data.BookData;
import com.aluracursos.literalura.helpers.TextTranslator;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private String language;
    @Column(length = 2000)
    private String summary;
    private Integer downloads;
    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;

    public Book() {
    }

    public Book(BookData data) {
        this.title = data.title();
        this.language = data.languages().get(0);
        // this.summary = data.summaries().get(0);
        this.summary = TextTranslator.translate(data.summaries().get(0));
        this.downloads = data.downloads();
    }

    @Override
    public String toString() {
        var message = """
                
                -----LIBRO-----
                Título: %s
                Autor: %s
                Idioma: %s
                Número de descargas: %d
                ---------------
                """;

        return String.format(message,
                title,
                author.getName(),
                Language.fromString(language),
                downloads);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
