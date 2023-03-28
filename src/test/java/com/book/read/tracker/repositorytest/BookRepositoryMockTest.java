package com.book.read.tracker.repositorytest;
import com.book.read.tracker.data.dao.BookDao;
import com.book.read.tracker.data.entity.Book;
import com.book.read.tracker.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class BookRepositoryMockTest {




        @Mock
        private BookRepository bookRepository;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void testBookRepositoryIsEmptyWhenNoBookAdded() {
            when(bookRepository.findAll()).thenReturn(Collections.emptyList());

            List<Book> allBooks = bookRepository.findAll();
            assertThat(allBooks).isEmpty();
        }

        @Test
        void testAddMultipleBooks() {
            BookDao firstBook = new BookDao();
            firstBook.setAuthor("J R R Tolkien");
            firstBook.setTitle("Lord Of The Rings");
            Book book = new Book(firstBook);

            BookDao secondBook = new BookDao();
            secondBook.setAuthor("J R R Tolkien");
            secondBook.setTitle("The Hobbit");
            Book bookTwo = new Book(secondBook);

            when(bookRepository.findAll()).thenReturn(List.of(book, bookTwo));

            List<Book> allBooks = bookRepository.findAll();
            assertThat(allBooks).hasSize(2);
        }

        @Test
        void testBookCanBeDeleted() {
            BookDao firstBook = new BookDao();
            firstBook.setAuthor("J R R Tolkien");
            firstBook.setTitle("Lord Of The Rings");
            Book book = new Book(firstBook);

            BookDao secondBook = new BookDao();
            secondBook.setAuthor("J R R Tolkien");
            secondBook.setTitle("The Hobbit");
            Book bookTwo = new Book(secondBook);

            when(bookRepository.findAll()).thenReturn(List.of(book, bookTwo));
            doNothing().when(bookRepository).delete(any(Book.class));

            bookRepository.delete(book);
            List<Book> deletedBook = bookRepository.findAll();
            assertThat(deletedBook).hasSize(1);
            assertThat(deletedBook.get(0).getId()).isEqualTo(bookTwo.getId());
        }

        @Test
        void testFindBookById() {
            BookDao firstBook = new BookDao();
            firstBook.setId(1L);
            firstBook.setAuthor("J R R Tolkien");
            firstBook.setTitle("Lord Of The Rings");
            Book book = new Book(firstBook);

            BookDao secondBook = new BookDao();
            secondBook.setId(2L);
            secondBook.setAuthor("J R R Tolkien");
            secondBook.setTitle("The Hobbit");
            Book bookTwo = new Book(secondBook);

            when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

            Optional<Book> bookFound = bookRepository.findById(1L);
            assertThat(bookFound).isPresent();
            assertThat(bookFound.get().getId()).isEqualTo(1);
        }

        // Add more test cases as needed
    }


