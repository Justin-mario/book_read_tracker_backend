package com.book.read.tracker.service.rating;

import com.book.read.tracker.data.dao.BookRatingDao;
import com.book.read.tracker.data.dto.BookDto;

public interface BookRatingService {
    BookDto addRating(Long bookId, BookRatingDao rating);
}
