package com.book.read.tracker.repository;

import com.book.read.tracker.data.bookenum.STATUS;
import com.book.read.tracker.data.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    List<Book> findAllByAuthor(String author);
    List<Book> findBooksByReadingProgress_Status(STATUS Status);

}