package com.example.demo.controllers;

import com.example.demo.entities.Book;
import com.example.demo.repositories.BookRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;


@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/api/books")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * El ResponseEntity permite devolver 404
     */
    @GetMapping("/api/books/{id}")
    @ApiOperation("Look for a book by its id") // Swagger anottation
    public ResponseEntity<Book> findById(@ApiParam("Clave primaria tipo Long") @PathVariable Long id) {

        Optional<Book> bookOpt = bookRepository.findById(id);
        return bookOpt.isPresent() ?
                ResponseEntity.ok(bookOpt.get())
                : ResponseEntity.notFound().build();

    }

    /**
     * El @RequestBody sirve para que parsear
     * la petición a Book
     */
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book) {

        if (book.getId() != null) {
            // Creado para mostrar warnings por consola
            // Mucho mejor que el sout
            log.warn("Trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }

        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);

    }

    /**
     * Actualizar libro
     */
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book) {

        if (book.getId() == null) {
            log.warn("Trying to update a book without id");
            return ResponseEntity.badRequest().build();
        }

        if (!bookRepository.existsById(book.getId())) {
            log.warn("Trying to update a non existing book");
            return ResponseEntity.notFound().build();
        }

        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);

    }

    @ApiIgnore // Ignore this method in the swagger doc
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {

        if (!bookRepository.existsById(id)) {
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();

    }

    @ApiIgnore
    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll() {

        log.info("REST Request for delete all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();

    }

}
