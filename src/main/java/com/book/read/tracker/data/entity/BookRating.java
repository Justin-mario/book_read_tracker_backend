package com.book.read.tracker.data.entity;


import com.book.read.tracker.data.dao.BookRatingDao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookRating {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rateBook = 0;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Book book;

    public BookRating(BookRatingDao bookRatingDao){
        id = bookRatingDao.getId ();
        rateBook = bookRatingDao.getRateBook ();
    }
}
