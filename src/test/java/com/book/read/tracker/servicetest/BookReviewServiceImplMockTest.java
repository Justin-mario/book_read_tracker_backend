package com.book.read.tracker.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.book.read.tracker.data.dao.BookReviewDao;
import com.book.read.tracker.data.dto.BookDto;
import com.book.read.tracker.data.entity.Book;
import com.book.read.tracker.data.entity.BookReview;
import com.book.read.tracker.data.entity.ReadingProgress;
import com.book.read.tracker.repository.BookReviewRepository;
import com.book.read.tracker.service.review.BookReviewServiceImpl;
import com.book.read.tracker.utility.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookReviewServiceImplMockTest {

    private BookReviewServiceImpl bookReviewService;

    @Mock
    private BookReviewRepository bookReviewRepository;

    @Mock
    private Utility utility;

    private Book book;

    private BookReview bookReview;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bookReviewService = new BookReviewServiceImpl(bookReviewRepository, utility);
        book = new Book();
        book.setId(1L);
        book.setReadingProgress ( new ReadingProgress () );
        bookReview = new BookReview();
    }

    @Test
    public void testAddReview() {
        bookReview.setReview("Great book");
        book.setBookReview(bookReview);

        BookReviewDao bookReviewDao = new BookReviewDao();
        bookReviewDao.setReview("Great book");

        when(utility.getBookById(1L)).thenReturn(book);
        when(bookReviewRepository.save(bookReview)).thenReturn(bookReview);

        BookDto result = bookReviewService.addReview(1L, bookReviewDao);
        assertEquals(result.getReview(), bookReviewDao.getReview());
    }

    @Test
    public void testAddReadingNote() {
        book.setBookReview(bookReview);
        BookReviewDao bookReviewDao = new BookReviewDao();
        bookReviewDao.setReadingNote("Interesting chapter");

        when(utility.getBookById(1L)).thenReturn(book);
        when(bookReviewRepository.save(bookReview)).thenReturn(bookReview);

        BookDto result = bookReviewService.addReadingNote(1L, bookReviewDao);
        assertEquals(result.getReadingNote(), bookReviewDao.getReadingNote());
    }
}
