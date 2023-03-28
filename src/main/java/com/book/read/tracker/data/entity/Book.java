package com.book.read.tracker.data.entity;


import com.book.read.tracker.data.dao.BookDao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title may not be blank")
    private String title;

    @NotBlank(message = "Author may not be blank")
    @NotNull
    private String author;

    private LocalDate dateAdded = LocalDate.now ();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ReadingProgress readingProgress;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BookReview bookReview;

    public Book(BookDao bookDAO) {
        id = bookDAO.getId ();
        title = bookDAO.getTitle ().toUpperCase ();
        author = bookDAO.getAuthor ().toUpperCase ();

    }



    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", dateAdded=" + dateAdded +
                ", readingProgress=" + readingProgress +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals ( id, book.id ) && Objects.equals ( title, book.title ) && Objects.equals ( author, book.author ) && Objects.equals ( dateAdded, book.dateAdded ) && Objects.equals ( readingProgress, book.readingProgress );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( id, title, author, dateAdded, readingProgress );
    }
}


