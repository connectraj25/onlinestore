package com.shop.book.repository;

import com.shop.book.entity.Book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
   Optional<Book> findByIsbn(String isbn);
   List<Book> findByAuthorOrName(String author, String name);
}
