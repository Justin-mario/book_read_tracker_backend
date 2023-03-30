package com.book.read.tracker.service.rating;

import com.book.read.tracker.data.dao.BookRatingDao;
import com.book.read.tracker.data.dto.BookDto;
import com.book.read.tracker.data.entity.Book;
import com.book.read.tracker.data.entity.BookRating;
import com.book.read.tracker.exception.BookException;
import com.book.read.tracker.repository.BookRatingRepository;
import com.book.read.tracker.utility.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookRatingServiceImpl implements BookRatingService{

    private final Utility utility;

    private final BookRatingRepository bookRatingRepository;

    public BookRatingServiceImpl(Utility utility, BookRatingRepository bookRatingRepository){
        this.utility= utility;
        this.bookRatingRepository = bookRatingRepository;
    }

    @Override
    public BookDto addRating(Long bookId, BookRatingDao rating) {
        Book book = utility.getBookById ( bookId );
        if (rating.getRateBook () < 0 || rating.getRateBook () > 5){
            throw new BookException ( "rating must be between 0 and 5" );
        }
        BookRating bookRating = book.getBookRating ();
        bookRating.setRateBook ( rating.getRateBook () );
        bookRatingRepository.save ( bookRating);
        return new BookDto (book);
    }
}
