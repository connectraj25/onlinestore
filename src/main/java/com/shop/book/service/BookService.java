package com.shop.book.service;

import com.shop.book.entity.Book;
import com.shop.book.entity.Checkout;

import java.util.Collection;
import java.util.List;

public interface BookService {
    /**
     * Get all Books
     * @return {@link Collection<Book>}
     */
    List<Book> getBooks();

    /**
     * Create a new book
     * @param book
     * @return {@link Book}
     */

    Book findBookById(Long id);

    Book createBook(Book book);

    /**
     * Update a new book
     * @param book
     * @return {@link Book}
     */
    Book updateBook(Book book);

    /**
     * delete the book by id has passed as an argument
     * @param id
     */
    void deleteBook(Long id);
    
    List<Book> findByAuthorOrName(List<Book> book);
    
    Checkout checkout(List<Book> book);
}
