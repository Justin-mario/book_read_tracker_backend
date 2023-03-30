package com.book.read.tracker.data.dao;

import com.book.read.tracker.data.entity.BookRating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookRatingDao {
    private Long id;

    private int rateBook;

    public BookRatingDao(BookRating bookRating){
        this.id = bookRating.getId ();
        rateBook = bookRating.getRateBook ();
    }
}
