package com.aluracursos.literalura.repositories;

import com.aluracursos.literalura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findAllByOrderByTitle();

    List<Book> findAllByLanguageOrderByTitle(String languageCode);

    List<Book> findTop10ByOrderByDownloadsDesc();

    @Query("SELECT b FROM Book b JOIN b.author a WHERE a.id = :authorId ORDER BY b.title")
    List<Book> findAllByAuthorId(Long authorId);
}
