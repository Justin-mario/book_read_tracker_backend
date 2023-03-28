package com.book.read.tracker.service.review;

import com.book.read.tracker.data.dao.BookReviewDao;
import com.book.read.tracker.data.dto.BookDto;

public interface BookReviewService {
    BookDto addReview(Long id, BookReviewDao review);
    BookDto addReadingNote(Long id, BookReviewDao note);
}
