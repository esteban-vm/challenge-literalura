package com.aluracursos.literalura.repositories;

import com.aluracursos.literalura.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameContainingIgnoreCase(String name);

    List<Author> findAllByOrderByName();

    @Query("SELECT a FROM Author a WHERE :year >= a.birthYear AND :year <= a.deathYear")
    List<Author> findAllByYear(Integer year);
}
