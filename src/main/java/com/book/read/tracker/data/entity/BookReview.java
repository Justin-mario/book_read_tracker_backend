package com.book.read.tracker.data.entity;


import com.book.read.tracker.data.dao.BookReviewDao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookReview {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String review;

    private String readingNote;

    @OneToOne
    private Book book;


    public BookReview(BookReviewDao bookReviewDao){
        id = bookReviewDao.getId ();
        review = bookReviewDao.getReview ();
        readingNote = bookReviewDao.getReadingNote ();
    }
}
