package com.book.read.tracker.servicetest;

import com.book.read.tracker.data.dao.BookRatingDao;
import com.book.read.tracker.data.dto.BookDto;
import com.book.read.tracker.data.entity.Book;
import com.book.read.tracker.data.entity.BookRating;
import com.book.read.tracker.data.entity.BookReview;
import com.book.read.tracker.data.entity.ReadingProgress;
import com.book.read.tracker.exception.BookException;
import com.book.read.tracker.repository.BookRatingRepository;
import com.book.read.tracker.service.rating.BookRatingService;
import com.book.read.tracker.service.rating.BookRatingServiceImpl;
import com.book.read.tracker.utility.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookRatingServiceImplMockTest {

    private Utility utility;

    private BookRatingService bookRatingService;

    private BookRatingRepository bookRatingRepository;

    @BeforeEach
    public void setUp(){
        bookRatingRepository = mock(BookRatingRepository.class);
        utility = mock ( Utility.class );
        bookRatingService = new BookRatingServiceImpl (utility , bookRatingRepository);
    }

    @Test
    public void testBookRatingWithValidNumber() {
        // Arrange
        long bookId = 1L;
        BookRatingDao bookRatingDao = new BookRatingDao ();
        bookRatingDao.setRateBook ( 3 );
        Book book = new Book();
        book.setBookRating ( new BookRating () );
        book.setReadingProgress ( new ReadingProgress () );
        book.setBookReview ( new BookReview () );
        when(utility.getBookById(bookId)).thenReturn(book);
        when ( bookRatingRepository.save ( book.getBookRating () ) ).thenReturn ( book.getBookRating () );

        BookDto result = bookRatingService.addRating ( bookId, bookRatingDao );

        // Assert
        assertEquals(result.getRating (), book.getBookRating ().getRateBook ());
    }

    @Test
    public void testBookRatingWithInvalidNumberBelowZero() {
      long bookId = 1L;
        BookRatingDao bookRatingDao = new BookRatingDao ();
        bookRatingDao.setRateBook ( -1 );
        Book book = new Book();
        book.setBookRating ( new BookRating () );
        book.setReadingProgress ( new ReadingProgress () );
        book.setBookReview ( new BookReview () );
        when(utility.getBookById(bookId)).thenReturn(book);
        when ( bookRatingRepository.save ( book.getBookRating () ) ).thenReturn ( book.getBookRating () );

        // Act & Assert
        assertThrows( BookException.class, () -> bookRatingService.addRating (bookId, bookRatingDao));
    }

    @Test
    public void testBookRatingWithInvalidNumberAboveFive() {
        long bookId = 1L;
        BookRatingDao bookRatingDao = new BookRatingDao ();
        bookRatingDao.setRateBook ( 6 );
        Book book = new Book();
        book.setBookRating ( new BookRating () );
        book.setReadingProgress ( new ReadingProgress () );
        book.setBookReview ( new BookReview () );
        when(utility.getBookById(bookId)).thenReturn(book);
        when ( bookRatingRepository.save ( book.getBookRating () ) ).thenReturn ( book.getBookRating () );

        // Act & Assert
        assertThrows( BookException.class, () -> bookRatingService.addRating (bookId, bookRatingDao));
    }
}
