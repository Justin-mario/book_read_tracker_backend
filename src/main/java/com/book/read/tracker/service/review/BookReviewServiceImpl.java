package com.book.read.tracker.service.review;

import com.book.read.tracker.data.dao.BookReviewDao;
import com.book.read.tracker.data.dto.BookDto;
import com.book.read.tracker.data.entity.Book;
import com.book.read.tracker.data.entity.BookReview;
import com.book.read.tracker.repository.BookReviewRepository;
import com.book.read.tracker.utility.Utility;
import org.springframework.stereotype.Service;

@Service
public class BookReviewServiceImpl implements BookReviewService{

    private final BookReviewRepository bookReviewRepository;

    private final Utility utility;

    public BookReviewServiceImpl(BookReviewRepository bookReviewRepository, Utility utility){
        this.bookReviewRepository = bookReviewRepository;
        this.utility = utility;
    }
    @Override
    public BookDto addReview(Long id, BookReviewDao review) {
        Book book = utility.getBookById ( id );
        book.getBookReview ().setReview ( review.getReview () );
        bookReviewRepository.save ( book.getBookReview ());
        return new BookDto (book);
    }

    @Override
    public BookDto addReadingNote(Long id, BookReviewDao note) {
        Book book = utility.getBookById ( id );
        book.getBookReview ().setReadingNote ( note.getReadingNote ());
        bookReviewRepository.save ( book.getBookReview ());
        return new BookDto (book);
    }
}
