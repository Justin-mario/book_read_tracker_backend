package com.book.read.tracker.service.book;

import com.book.read.tracker.data.dao.BookDao;
import com.book.read.tracker.data.dto.BookDto;
import com.book.read.tracker.data.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface BookService {
    BookDto addBook (BookDao bookDAO);
    BookDto getBookByTitle(String title);
    BookDto getById(Long id);
    List<BookDto > getAllBooksByAuthor(String author);
    List<BookDto > getAllBooks();
    Map<String, String> deleteBookByTitle(String title);
    Map<String, String> deleteAllBooksByAuthor(String author);
    Map<String, String> deleteAllBooks();
}
