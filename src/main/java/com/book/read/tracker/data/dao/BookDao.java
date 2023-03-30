package com.book.read.tracker.data.dao;

import com.book.read.tracker.data.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDao {
    private Long id;

    private String author;

    private String title;

    private String month;

    private String year;

    public BookDao(Book book) {
        id = book.getId ();
        author = book.getAuthor ();
        title = book.getTitle ();
    }
}
