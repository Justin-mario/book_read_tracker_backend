package com.book.read.tracker.data.dao;

import com.book.read.tracker.data.entity.BookReview;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookReviewDao {

    private Long id;

    private String review;

    private String readingNote;

    public BookReviewDao(BookReview bookReview){
        id = bookReview.getId ();
        review = bookReview.getReview ();
        readingNote = bookReview.getReadingNote ();
    }
}
