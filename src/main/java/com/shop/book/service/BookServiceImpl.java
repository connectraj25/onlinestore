package com.shop.book.service;

import com.shop.book.entity.Book;
import com.shop.book.entity.Checkout;
import com.shop.book.exception.ResourceAlreadyExistsException;
import com.shop.book.exception.ResourceNotFoundException;
import com.shop.book.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
 Map <String,Double> discountMap=new HashMap<String,Double>();
  public BookServiceImpl() {
	  discountMap.put("fiction", 0.20);
	  discountMap.put("comic", 0.10);
  }
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Book createBook(Book book) {
        Optional<Book> bookFromDb =bookRepository.findByIsbn(book.getIsbn());
        if (!bookFromDb.isPresent()) {
            log.info("Saving {}", book);
            return bookRepository.save(book);
        } else {
            throw new ResourceAlreadyExistsException("Resource Already Exists :" + bookFromDb.get());
        }
    }

    @Override
    public Book updateBook(Book book) {
        Optional<Book> bookFromDb = this.bookRepository.findById(book.getId());
        if (bookFromDb.isPresent()) {
            Book updatedBook = bookFromDb.get();
            BeanUtils.copyProperties(book, updatedBook);
            log.info("Updating {}", updatedBook);
            return bookRepository.save(updatedBook);
        } else {
            throw new ResourceNotFoundException("Book not found with id : " + book.getId());
        }
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Book> bookFromDb = this.bookRepository.findById(id);
        if (bookFromDb.isPresent()) {
            Book deletedBook = bookFromDb.get();
            log.info("Deleting {}", deletedBook);
            bookRepository.delete(deletedBook);
        } else {
            throw new ResourceNotFoundException("Book not found with id : " + id);
        }
    }

	@Override
	public List<Book> findByAuthorOrName(List<Book> books) {
		// TODO Auto-generated method stub
		return books.stream().map(book ->bookRepository.findByAuthorOrName(book.getAuthor(), book.getName()))
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		
	}

	@Override
	public Checkout checkout(List<Book> books) {
		Checkout checkout=new Checkout();
		String msg=checkAvailability(books);
		List<String> bookList=new ArrayList<>();
		if(StringUtils.isEmpty(msg)) {
			 Double total=books.stream().map(book ->{
                 Book bookEntity=bookRepository.findByIsbn(book.getIsbn()).get();
                 bookEntity.setQuantity(bookEntity.getQuantity()-book.getQuantity());
                 bookList.add(bookEntity.getName());
                 bookRepository.save(bookEntity);
                 return bookEntity.getPrice()*book.getQuantity()*(1-discountMap.get(bookEntity.getType()));
             })
			 .reduce(0.0, (subtotal, element) -> subtotal + element);
            checkout.setTotalPrice(total);
            checkout.setName(bookList);
            checkout.setMessage("Checkout successful :) ");
			 }

		else {
			//checkout.setMessage("Checkout failed due to inventory shortage  ");
			checkout.setMessage(msg);
		}
		return checkout;
	}
	
	private String checkAvailability(List<Book> books){
		Map<String,Object> map = new HashMap<String,Object>();
		StringBuilder builder=new StringBuilder();
		boolean isAvailable=true;
		System.out.print(bookRepository.findByIsbn(books.get(0).getIsbn()));
		System.out.print(bookRepository.findByIsbn(books.get(1).getIsbn()));
		for(Book book:books) {
			bookRepository.findByIsbn(book.getIsbn())
		
			 
			 .ifPresentOrElse((value)
	                -> { System.out.println(
	                         "Value is present, its: "
	                         + value); 
	                if(book.getQuantity()> value.getQuantity())
	               // book.getQuantity()>value.ge
	                builder.append(value.getName() + " is doesn't have sufficient quantity in stock.");
	                },
	            ()
	                -> { 
	                	System.out.println(
	                       "Value is empty");
	                	builder.append(book.getIsbn() + " is not present.");
	                });
//		 return "";
//		 });
//				.flatMap(Collection::stream)
//				.collect(Collectors.toList());
		}
		return builder.toString();
	}
}
