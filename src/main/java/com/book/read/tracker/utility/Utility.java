package com.book.read.tracker.utility;

import com.book.read.tracker.data.entity.Book;
import com.book.read.tracker.exception.BookNotFoundException;
import com.book.read.tracker.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Utility {

    private final BookRepository bookRepository;

    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById ( id );
        return book.orElseThrow (()->new BookNotFoundException ("Book with id " + id + " does not exist"));
    }
}
