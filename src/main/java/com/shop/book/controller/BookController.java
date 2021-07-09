package com.shop.book.controller;

import com.shop.book.entity.Book;
import com.shop.book.entity.Checkout;
import com.shop.book.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping("/api")
public class BookController {
    private final Logger log = LoggerFactory.getLogger(BookController.class);
    private final String rootURI = "/api";

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        log.info("Invoked - {}/books", rootURI);
        return new ResponseEntity<List<Book>>(bookService.getBooks(), HttpStatus.OK);

    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {
        log.info("Invoked - {}/findbookbyId/{}", rootURI, id);
        return new ResponseEntity<Book>(bookService.findBookById(id), HttpStatus.OK);
    }


    @PostMapping(value = "/createbook")
    public ResponseEntity<Book> saveBook(@Valid @RequestBody Book book) {
        log.info("Invoked - {}/createbook", rootURI);
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @PutMapping(value = "/updatebook")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book) {
        log.info("Invoked - {}/updatebook", rootURI);
        return new ResponseEntity<>(bookService.updateBook(book), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deletebook/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {
        log.info("Invoked - {}/deletebook/{}", rootURI, id);
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
    @PostMapping("/search")
    public ResponseEntity<List<Book>> searchBook(@Valid @RequestBody List<Book> books) {
        log.info("Invoked - {}/updatebook", rootURI);
        return new ResponseEntity<>(bookService.findByAuthorOrName(books), HttpStatus.OK);
    }
    
    @PostMapping("/checkout")
    public ResponseEntity<Checkout> checkoutBooks(@Valid @RequestBody List<Book> books) {
        log.info("Invoked - {}/updatebook", rootURI);
        return new ResponseEntity<Checkout>(bookService.checkout(books), HttpStatus.OK);
    }
    
}
