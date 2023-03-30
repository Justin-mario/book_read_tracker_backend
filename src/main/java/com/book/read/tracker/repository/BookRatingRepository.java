package com.book.read.tracker.repository;

import com.book.read.tracker.data.entity.BookRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRatingRepository extends JpaRepository<BookRating, Long> {
}
