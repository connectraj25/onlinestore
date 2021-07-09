package com.shop.book.repository;

import com.shop.book.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void should_find_books_by_isbn() {
        Book book1 = new Book();
        book1.setId(10L);
        book1.setName("You are a perfect Man");
        book1.setDescription("Sample Desc");
        book1.setAuthor("Raju");
        book1.setType("comic");
        book1.setPrice(25.0);
        book1.setIsbn("1234567433");
        book1.setQuantity(2);
        entityManager.persist(book1);


        Book book2 = new Book();
        book2.setId(11L);
        book2.setName("You are a perfect Man");
        book2.setDescription("Sample Desc");
        book2.setAuthor("Raju");
        book2.setType("comic");
        book2.setPrice(25.0);
        book2.setIsbn("1234567433");
        book2.setQuantity(2);
        entityManager.persist(book2);

        Book foundBook = bookRepository.findByIsbn(book2.getIsbn()).get();

        org.assertj.core.api.Assertions.assertThat(foundBook).isEqualTo(book2);

    }
//
//    @Test
//    void findByAuthorOrName() {
//    }
}