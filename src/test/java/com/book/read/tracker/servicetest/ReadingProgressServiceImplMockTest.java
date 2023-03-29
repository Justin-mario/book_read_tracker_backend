package com.book.read.tracker.servicetest;//package com.book.read.tracker.servicetest;
//
//import com.book.read.tracker.data.bookenum.STATUS;
//import com.book.read.tracker.data.entity.Book;
//import com.book.read.tracker.data.entity.ReadingProgress;
//import com.book.read.tracker.exception.BookException;
//import com.book.read.tracker.repository.ReadingProgressRepository;
//import com.book.read.tracker.service.progress.ReadingProgressServiceImpl;
//import com.book.read.tracker.utility.Utility;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class ReadingProgressServiceImplMockTest {
//
//    @Mock
//    private ReadingProgressRepository progressRepository;
//
//    @Mock
//    private Utility utility;
//
//    @InjectMocks
//    private ReadingProgressServiceImpl readingStatusService;
//
//    private Book book;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        book = new Book();
//        book.setId(1L);
//        book.setTitle("Test Book");
//        book.setReadingProgress(new ReadingProgress ());
//    }
//
//
//    @Test
//    @DisplayName("Test setReadStatus with FINISHED status")
//    public void testSetReadStatusWithFinishedStatus() {
//        when(utility.getBookById(any())).thenReturn(book);
//        readingStatusService.setReadStatus(1L, , "FINISHED");
//        verify(progressRepository).save(book.getReadingProgress());
//        assert(book.getReadingProgress().getStatus() == STATUS.FINISHED);
//        assert(book.getReadingProgress().isFinished());
//    }
//
//    @Test
//    @DisplayName("Test setReadStatus with YET_TO_READ status")
//    public void testSetReadStatusWithYetToReadStatus() {
//        when(utility.getBookById(any())).thenReturn(book);
//        readingStatusService.setReadStatus(1L, , "YET_TO_READ");
//        verify(progressRepository).save(book.getReadingProgress());
//        assert(book.getReadingProgress().getStatus() == STATUS.YET_TO_READ);
//        assert(!book.getReadingProgress().isFinished());
//    }
//
//
//    @Test
//    @DisplayName("Test setDaily_weeklyReadingTarget with valid input")
//    public void testSetDailyWeeklyReadingTargetWithValidInput() {
//        when(utility.getBookById(any())).thenReturn(book);
//        Assertions.assertEquals("success", readingStatusService.setDaily_weeklyReadingTarget(1L, , 50));
//    }
//
//    @Test
//    @DisplayName("Test setDaily_weeklyReadingTarget with invalid input")
//    public void testSetDailyWeeklyReadingTargetWithInvalidInput() {
//        when(utility.getBookById(any())).thenReturn(book);
//        Assertions.assertThrows(BookException.class, () -> {
//            readingStatusService.setDaily_weeklyReadingTarget(1L, , -1);
//        });
//    }
//
//    @Test
//    @DisplayName("Test setCurrentPage with valid input")
//    public void testSetCurrentPageWithValidInput() {
//        when(utility.getBookById(any())).thenReturn(book);
//        Assertions.assertEquals("success", readingStatusService.setCurrentPage(1L, 100));
//    }
//
//    @Test
//    @DisplayName("Test setCurrentPage with invalid input")
//    public void testSetCurrentPageWithInvalidInput() {
//        when(utility.getBookById(any())).thenReturn(book);
//        Assertions.assertThrows(BookException.class, () -> {
//            readingStatusService.setCurrentPage(1L, -1);
//        });
//    }
//
//    @Test
//    @DisplayName("Test setReadStatus with valid input")
//    public void testSetReadStatusWithValidInput() {
//        when(utility.getBookById(any())).thenReturn(book);
//        Assertions.assertEquals("success", readingStatusService.setReadStatus(1L, , "IN_PROGRESS"));
//        assert(!book.getReadingProgress().isFinished());
//    }
//
//    @Test
//    @DisplayName("Test setReadStatus with invalid input")
//    public void testSetReadStatusWithInvalidInput() {
//        when(utility.getBookById(any())).thenReturn(book);
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            readingStatusService.setReadStatus(1L, , "invalidStatus");
//        });
//    }
//
//
//}


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.book.read.tracker.data.dao.ReadingProgressDao;
import com.book.read.tracker.data.entity.Book;
import com.book.read.tracker.data.entity.ReadingProgress;
import com.book.read.tracker.exception.BookException;
import com.book.read.tracker.repository.ReadingProgressRepository;
import com.book.read.tracker.service.progress.ReadingProgressService;
import com.book.read.tracker.service.progress.ReadingProgressServiceImpl;
import com.book.read.tracker.utility.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadingProgressServiceImplMockTest  {

    private Utility utility;
    private ReadingProgressService readingProgressService;

    @BeforeEach
    public void setUp() {
        ReadingProgressRepository progressRepository = mock ( ReadingProgressRepository.class );
        utility = mock(Utility.class);
        readingProgressService = new ReadingProgressServiceImpl ( progressRepository, utility);
    }

    @Test
    public void testSetDaily_weeklyReadingTarget_success() {
        // Arrange
        long bookId = 1L;
        ReadingProgressDao target = new ReadingProgressDao();
        target.setSetNumberOfPageToReadTarget(10);
        Book book = new Book();
        book.setReadingProgress ( new ReadingProgress () );
        when(utility.getBookById(bookId)).thenReturn(book);

        // Act
        String result = readingProgressService.setDaily_weeklyReadingTarget(bookId, target);

        // Assert
        assertEquals("success", result);
        assertEquals(10, book.getReadingProgress().getSetNumberOfPageToReadTarget());
    }

    @Test
    public void testSetDaily_weeklyReadingTarget_invalidInput() {
        // Arrange
        long bookId = 1L;
        ReadingProgressDao target = new ReadingProgressDao();
        target.setSetNumberOfPageToReadTarget(-1);
        Book book = new Book();
        book.setReadingProgress ( new ReadingProgress () );
        when(utility.getBookById(bookId)).thenReturn(book);

        // Act & Assert
        assertThrows(BookException.class, () -> readingProgressService.setDaily_weeklyReadingTarget(bookId, target));
    }

    @Test
    public void testSetReadStatusWithFinishedStatus() {
        // Arrange
        long bookId = 1L;
        Book book = new Book();
        book.setReadingProgress ( new ReadingProgress () );
        ReadingProgressDao progressDao = new ReadingProgressDao ();
        progressDao.setStatus ( "FINISHED" );
        when(utility.getBookById(bookId)).thenReturn(book);

        // Act
        String result = readingProgressService.setReadStatus(bookId, progressDao);

        // Assert
        assertEquals("success", result);
        assertEquals(progressDao.getStatus (), book.getReadingProgress ().getStatus ().toString ());
    }

    @Test
    public void testSetReadStatusWithYetToReadStatus() {
        // Arrange
        long bookId = 1L;
        Book book = new Book();
        book.setReadingProgress ( new ReadingProgress () );
        ReadingProgressDao progressDao = new ReadingProgressDao ();
        progressDao.setStatus ( "YET_TO_READ" );
        when(utility.getBookById(bookId)).thenReturn(book);

        // Act
        String result = readingProgressService.setReadStatus(bookId, progressDao);

        // Assert
        assertEquals("success", result);
        assertEquals(progressDao.getStatus (), book.getReadingProgress().getStatus ().toString ());
    }

    @Test
    public void testSetCurrentPageWithValidInput() {
        // Arrange
        long bookId = 1L;
        Book book = new Book();
        book.setReadingProgress ( new ReadingProgress () );
        ReadingProgressDao progressDao = new ReadingProgressDao ();
        progressDao.setCurrentPage ( 50 );
        when(utility.getBookById(bookId)).thenReturn(book);

        // Act
        String result = readingProgressService.setCurrentPage(bookId, progressDao);

        // Assert
        assertEquals("success", result);
        assertEquals(progressDao.getCurrentPage (), book.getReadingProgress().getCurrentPage());
    }

    @Test
    public void testSetCurrentPageWithInvalidInput() {
        // Arrange
        long bookId = 1L;
        Book book = new Book();
        book.setReadingProgress ( new ReadingProgress () );
        ReadingProgressDao progressDao = new ReadingProgressDao ();
        progressDao.setCurrentPage ( -1 );
        when(utility.getBookById(bookId)).thenReturn(book);

        // Act & Assert
        assertThrows(BookException.class, () -> readingProgressService.setCurrentPage(bookId, progressDao));
    }

    @Test
    public void testSetReadStatusWithReadingInput() {
        // Arrange
        long bookId = 1L;
        Book book = new Book();
        book.setReadingProgress ( new ReadingProgress () );
        ReadingProgressDao progressDao = new ReadingProgressDao ();
        progressDao.setStatus ( "IN_PROGRESS" );
        when(utility.getBookById(bookId)).thenReturn(book);

        // Act
        String result = readingProgressService.setReadStatus(bookId, progressDao);

        // Assert
        assertEquals("success", result);
        assertEquals(progressDao.getStatus (), book.getReadingProgress().getStatus ().toString ());
    }

    @Test
    public void testSetReadStatusWithInvalidInput() {
        // Arrange
        long bookId = 1L;
        Book book = new Book();
        book.setReadingProgress ( new ReadingProgress () );
        when(utility.getBookById(bookId)).thenReturn(book);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> readingProgressService.setReadStatus(bookId, null));
    }

}


