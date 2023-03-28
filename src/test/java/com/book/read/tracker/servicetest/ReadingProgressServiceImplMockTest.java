package com.book.read.tracker.servicetest;

import com.book.read.tracker.data.bookenum.STATUS;
import com.book.read.tracker.data.entity.Book;
import com.book.read.tracker.data.entity.ReadingProgress;
import com.book.read.tracker.exception.BookException;
import com.book.read.tracker.repository.ReadingProgressRepository;
import com.book.read.tracker.service.progress.ReadingProgressServiceImpl;
import com.book.read.tracker.utility.Utility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReadingProgressServiceImplMockTest {

    @Mock
    private ReadingProgressRepository progressRepository;

    @Mock
    private Utility utility;

    @InjectMocks
    private ReadingProgressServiceImpl readingStatusService;

    private Book book;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setReadingProgress(new ReadingProgress ());
    }


    @Test
    @DisplayName("Test setReadStatus with FINISHED status")
    public void testSetReadStatusWithFinishedStatus() {
        when(utility.getBookById(any())).thenReturn(book);
        readingStatusService.setReadStatus(1L, "FINISHED");
        verify(progressRepository).save(book.getReadingProgress());
        assert(book.getReadingProgress().getStatus() == STATUS.FINISHED);
        assert(book.getReadingProgress().isFinished());
    }

    @Test
    @DisplayName("Test setReadStatus with YET_TO_READ status")
    public void testSetReadStatusWithYetToReadStatus() {
        when(utility.getBookById(any())).thenReturn(book);
        readingStatusService.setReadStatus(1L, "YET_TO_READ");
        verify(progressRepository).save(book.getReadingProgress());
        assert(book.getReadingProgress().getStatus() == STATUS.YET_TO_READ);
        assert(!book.getReadingProgress().isFinished());
    }


    @Test
    @DisplayName("Test setDaily_weeklyReadingTarget with valid input")
    public void testSetDailyWeeklyReadingTargetWithValidInput() {
        when(utility.getBookById(any())).thenReturn(book);
        Assertions.assertEquals("success", readingStatusService.setDaily_weeklyReadingTarget(1L, 50));
    }

    @Test
    @DisplayName("Test setDaily_weeklyReadingTarget with invalid input")
    public void testSetDailyWeeklyReadingTargetWithInvalidInput() {
        when(utility.getBookById(any())).thenReturn(book);
        Assertions.assertThrows(BookException.class, () -> {
            readingStatusService.setDaily_weeklyReadingTarget(1L, -1);
        });
    }

    @Test
    @DisplayName("Test setCurrentPage with valid input")
    public void testSetCurrentPageWithValidInput() {
        when(utility.getBookById(any())).thenReturn(book);
        Assertions.assertEquals("success", readingStatusService.setCurrentPage(1L, 100));
    }

    @Test
    @DisplayName("Test setCurrentPage with invalid input")
    public void testSetCurrentPageWithInvalidInput() {
        when(utility.getBookById(any())).thenReturn(book);
        Assertions.assertThrows(BookException.class, () -> {
            readingStatusService.setCurrentPage(1L, -1);
        });
    }

    @Test
    @DisplayName("Test setReadStatus with valid input")
    public void testSetReadStatusWithValidInput() {
        when(utility.getBookById(any())).thenReturn(book);
        Assertions.assertEquals("success", readingStatusService.setReadStatus(1L, "IN_PROGRESS"));
        assert(!book.getReadingProgress().isFinished());
    }

    @Test
    @DisplayName("Test setReadStatus with invalid input")
    public void testSetReadStatusWithInvalidInput() {
        when(utility.getBookById(any())).thenReturn(book);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            readingStatusService.setReadStatus(1L, "invalidStatus");
        });
    }


}

