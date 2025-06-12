package com.aluracursos.literalura.main;

import com.aluracursos.literalura.data.ResponseData;
import com.aluracursos.literalura.helpers.APIConsumer;
import com.aluracursos.literalura.helpers.DataConversor;
import com.aluracursos.literalura.helpers.StringUtils;
import com.aluracursos.literalura.models.Author;
import com.aluracursos.literalura.models.Book;
import com.aluracursos.literalura.models.Language;
import com.aluracursos.literalura.repositories.AuthorRepository;
import com.aluracursos.literalura.repositories.BookRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final APIConsumer consumer = new APIConsumer();
    private final DataConversor conversor = new DataConversor();

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public Main(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        var menu = """
                
                -----------------------------------------------
                Elija la opción a través de su número:
                1 - Buscar libro por título.
                2 - Listar libros registrados.
                3 - Listar autores registrados.
                4 - Listar autores vivos en un determinado año.
                5 - Listar libros por idioma.
                6 - Listar libros por autor.
                7 - Listar los 10 libros más descargados.
                8 - Mostrar estadísticas.
                0 - Salir
                -----------------------------------------------
                """;

        var option = -1;

        while (option != 0) {
            System.out.println(menu);

            if (!scanner.hasNextInt()) {
                System.out.println("Ingrese una opción válida");
                scanner.next();
                continue;
            }

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> searchBookByTitle();
                case 2 -> listRegisteredBooks();
                case 3 -> listRegisteredAuthors();
                case 4 -> listAliveAuthorsByYear();
                case 5 -> listBooksByLanguage();
                case 6 -> listBooksByAuthor();
                case 7 -> listTop10BooksDownloaded();
                case 8 -> showStats();
                case 0 -> System.out.println("Cerrando la aplicación…");
                default -> System.out.println("Opción inválida");
            }
        }

        scanner.close();
    }

    private void searchBookByTitle() {
        System.out.println("\nEscriba el título del libro que desea buscar:");

        var inputTitle = scanner.nextLine();
        var encodedTitle = StringUtils.encodeTitle(inputTitle);
        var responseJson = consumer.getDataFromAPI(encodedTitle);
        var responseData = conversor.getData(responseJson, ResponseData.class);
        var results = responseData.results();

        if (results.isEmpty()) {
            System.out.println("Sin resultados.");
        } else {
            var bookData = results.get(0);
            var bookFromDB = bookRepository.findByTitleContainingIgnoreCase(bookData.title());

            if (bookFromDB.isPresent()) {
                var existingBook = bookFromDB.get();
                System.out.println("El libro '" + existingBook.getTitle() + "' ya está registrado.");
            } else {
                var newBook = new Book(bookData);
                var authorData = bookData.authors().get(0);
                var savedAuthor = authorRepository.findByNameContainingIgnoreCase(authorData.name());
                var author = savedAuthor.orElseGet(() -> new Author(authorData));
                newBook.setAuthor(author);
                authorRepository.save(author);
                bookRepository.save(newBook);
                System.out.println("Libro registrado correctamente: " + newBook.getTitle());
            }
        }

    }

    private void listRegisteredBooks() {
        System.out.println("\nLibros registrados:");
        var books = bookRepository.findAllByOrderByTitle();
        printList(books);
    }

    private void listRegisteredAuthors() {
        System.out.println("\nAutores registrados:");
        var authors = authorRepository.findAllByOrderByName();
        printList(authors);
    }

    private void listAliveAuthorsByYear() {
        System.out.println("\nIngrese el año del que desea listar los autores:");
        var inputYear = scanner.nextInt();
        var authors = authorRepository.findAllByYear(inputYear);
        System.out.println("\nAutores vivos en " + inputYear + ":");
        printList(authors);
    }

    private void listBooksByLanguage() {
        System.out.println("\nIngrese el código del idioma del cual desea listar los libros:");

        for (Language language : Language.values()) {
            System.out.println(language.getLanguageCode() + " (" + language + ")");
        }

        var inputLanguage = scanner.nextLine();
        var language = Language.fromString(inputLanguage);
        var books = bookRepository.findAllByLanguageOrderByTitle(language.getLanguageCode());

        System.out.println("\nLibros en " + language + ":");
        printList(books);
    }

    private void listBooksByAuthor() {
        System.out.println("\nIngrese el número del autor del cual desea listar los libros:");
        var authors = authorRepository.findAll();

        authors.stream()
                .sorted(Comparator.comparing(Author::getId))
                .forEach(author -> {
                    System.out.println(author.getId() + " - " + author.getName());
                });

        var inputNumber = scanner.nextLong();
        var authorFromDB = authorRepository.findById(inputNumber);

        if (authorFromDB.isPresent()) {
            var author = authorFromDB.get();
            var books = bookRepository.findAllByAuthorId(author.getId());
            printList(books);
        }
    }

    private void listTop10BooksDownloaded() {
        System.out.println("\nLos 10 libros más descargados:");
        var books = bookRepository.findTop10ByOrderByDownloadsDesc();
        printList(books);
    }

    private void showStats() {
        var books = bookRepository.findAll();

        // System.out.println("Cantidad de libros por idioma:");
        // books.stream()
        //         .collect(Collectors.groupingBy(
        //                 book -> Language.fromString(book.getLanguage()),
        //                 Collectors.counting()))
        //         .forEach((language, count) -> {
        //             System.out.println(language + ": " + count);
        //         });

        for (Language language : Language.values()) {
            System.out.println("\nEstadísticas de libros en " + language);

            var stats = books.stream()
                    .filter(book -> Objects
                            .equals(book.getLanguage(), language.getLanguageCode()))
                    .collect(Collectors.summarizingInt(Book::getDownloads));

            System.out.println("Cantidad de libros: " + stats.getCount());
            System.out.println("Total de descargas: " + stats.getSum());
        }
    }

    private <T> void printList(List<T> list) {
        if (list.isEmpty()) {
            System.out.println("Sin resultados.");
        } else {
            list.forEach(System.out::println);
        }
    }
}
