package com.shop.book.controller;

import com.shop.book.entity.Book;
import com.shop.book.repository.BookRepository;
import com.shop.book.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@DataJpaTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Mock
//    private BookRepository bookRepository;

    @MockBean
    private BookService bookService;


    @Test
    void getBooks()  throws Exception{
        Book book1 = new Book();
        book1.setName("You are a perfect Man");
        book1.setDescription("Sample Desc");
        book1.setAuthor("Raju");
        book1.setType("comic");
        book1.setPrice(25.0);
        book1.setIsbn("1234567433");
        book1.setQuantity(2);

        Book book2 = new Book();
        book2.setName("You are a perfect Man");
        book2.setDescription("Sample Desc");
        book2.setAuthor("Raju");
        book2.setType("comic");
        book2.setPrice(25.0);
        book2.setIsbn("1234567433");
        book2.setQuantity(2);


    }

//    @Test
//    void getBook() {
//    }
//
//    @Test
//    void saveBook() {
//    }
//
//    @Test
//    void updateBook() {
//    }
//
//    @Test
//    void deleteBook() {
//    }
//
//    @Test
//    void searchBook() {
//    }
//
//    @Test
//    void checkoutBooks() {
//    }
}