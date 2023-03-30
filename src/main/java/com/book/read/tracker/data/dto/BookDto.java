package com.book.read.tracker.data.dto;

import com.book.read.tracker.data.bookenum.STATUS;
import com.book.read.tracker.data.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {


    private Long id;

    private String title;

    private String author;

    private boolean isFinished;

    private int setNumberOfPageToReadTarget;

    private int currentPage;

    private STATUS status;

    private String review;

    private String readingNote;

    private int rating;

    public BookDto(Book book){
        id = book.getId ();
        title = book.getTitle ();
        author = book.getAuthor ();
        isFinished = book.getReadingProgress ().isFinished ();
        setNumberOfPageToReadTarget = book.getReadingProgress ().getSetNumberOfPageToReadTarget ();
        currentPage = book.getReadingProgress ().getCurrentPage ();
        status = book.getReadingProgress ().getStatus ();
        review = book.getBookReview ().getReview ();
        readingNote = book.getBookReview ().getReadingNote ();
        rating = book.getBookRating ().getRateBook ();
    }
}
