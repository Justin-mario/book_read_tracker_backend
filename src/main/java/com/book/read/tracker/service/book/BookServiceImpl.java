package com.book.read.tracker.service.book;

import com.book.read.tracker.data.bookenum.STATUS;
import com.book.read.tracker.data.dao.BookDao;
import com.book.read.tracker.data.dto.BookDto;
import com.book.read.tracker.data.entity.Book;
import com.book.read.tracker.data.entity.BookRating;
import com.book.read.tracker.data.entity.BookReview;
import com.book.read.tracker.data.entity.ReadingProgress;
import com.book.read.tracker.exception.BookException;
import com.book.read.tracker.exception.BookExistException;
import com.book.read.tracker.exception.BookNotFoundException;
import com.book.read.tracker.repository.BookRatingRepository;
import com.book.read.tracker.repository.BookRepository;
import com.book.read.tracker.repository.BookReviewRepository;
import com.book.read.tracker.repository.ReadingProgressRepository;
import com.book.read.tracker.utility.Utility;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final ReadingProgressRepository progressRepository;

    private final BookReviewRepository bookReviewRepository;

    private final BookRatingRepository bookRatingRepository;

    private final Utility utility;

    @Transactional
    @Override
    public BookDto addBook(BookDao bookDAO) {
        bookExist ( bookDAO );
        Book book = new Book (bookDAO);
        ReadingProgress readingProgress = new ReadingProgress ();
        book.setReadingProgress ( readingProgress );
        readingProgress.setBook ( book );
        BookReview bookReview = new BookReview ();
        bookReview.setBook ( book );
        book.setBookReview ( bookReview );
        BookRating bookRating = new BookRating ();
        bookRating.setBook ( book );
        book.setBookRating ( bookRating );
        progressRepository.save ( readingProgress );
        bookRepository.save ( book );
        bookReviewRepository.save ( bookReview );
        bookRatingRepository.save ( bookRating );
        return new BookDto (book);
    }

    public BookDto getById(Long id){
        Book book = utility.getBookById ( id );
        return new BookDto (book);
    }



    @Override
    public BookDto getBookByTitle(String title) {
        Optional<Book> book = bookRepository.findByTitle ( title.toUpperCase () );
        return new BookDto (book.orElseThrow (()->new BookNotFoundException ("Book with title " + title + " does not exist")));
    }

    @Override
    public List<BookDto > getAllBooksByAuthor(String author) {
        List<Book> allBooksByAuthor = bookRepository.findAllByAuthor ( author.toUpperCase () );
        return allBooksByAuthor.stream ().map ( BookDto::new ).toList ();
    }

    @Override
    public List<BookDto > getAllBooks(){
        List<Book> allBooks = bookRepository.findAll ();
        return allBooks.stream ().map ( BookDto::new ).toList ();
    }

    @Override
    public Map<String, String> deleteBookByTitle(String title){
        Book book = bookExistByTitle ( title.toUpperCase () );
        bookRepository.deleteById ( book.getId () );
        Map<String, String> status = new HashMap<> ();
        status.put ( "Success", title+" has been deleted" );
        return status;
    }

    @Override
    public Map<String, String> deleteAllBooksByAuthor(String author) {
        List<Book> allBooksByAuthor = bookExistByAuthor ( author.toUpperCase () );
        allBooksByAuthor.forEach ( book -> bookRepository.deleteById ( book.getId () ));
        Map<String, String> status = new HashMap<> ();
        status.put ( "Success", "All books by " + author+ " has been deleted" );
        return status;
    }

    @Override
    public Map<String, String> deleteAllBooks(){
        containsBook ();
        bookRepository.deleteAll ();
        Map<String, String> status = new HashMap<> ();
        status.put ( "Success", "All books  has been deleted");
        return status;
    }

    @Override
    public List<BookDto> getAllReadBooks() {
        List<Book> allBooksRead = bookRepository.findBooksByReadingProgress_Status ( STATUS.FINISHED );
        return allBooksRead.stream ().map ( BookDto::new ).toList ();
    }

    @Override
    public List<BookDto> getAllYetToReadBooks() {
        List<Book> allYetToReadBooks = bookRepository.findBooksByReadingProgress_Status ( STATUS.YET_TO_READ );
        return allYetToReadBooks.stream ().map ( BookDto::new ).toList ();
    }

    @Override
    public List<BookDto> getAllInProcessBook() {
        List<Book> allYetToReadBooks = bookRepository.findBooksByReadingProgress_Status ( STATUS.IN_PROGRESS );
        return allYetToReadBooks.stream ().map ( BookDto::new ).toList ();
    }

    private Book bookExistByTitle(String title){
       Optional<Book> book= bookRepository.findByTitle ( title );
       return book.orElseThrow (()-> new BookNotFoundException ( "Book with title " + title +" does not exist" ));
    }

    private List<Book> bookExistByAuthor(String author){
        List<Book> booksByAuthor = bookRepository.findAllByAuthor ( author );
        if (booksByAuthor.isEmpty ()){
            throw new BookNotFoundException ( "No book with " + author + " as author");
        }
        return booksByAuthor;
    }

    private void containsBook(){
        List<Book> bookList = bookRepository.findAll ();
        if (bookList.isEmpty ()){
            throw new BookException ("no books in the database");
        }
    }

    private void bookExist(BookDao bookDAO){
        List<Book> allBooks = bookRepository.findAll ();
        allBooks.forEach ( book -> {
            if (book.getTitle ().equals ( bookDAO.getTitle ().toUpperCase () ) && book.getAuthor ().equals ( bookDAO.getAuthor ().toUpperCase () )){
                throw new BookExistException ("Book with title " + bookDAO.getTitle ()+ " authored by " + bookDAO.getAuthor () + " exists in the database");
            }
        } );



    }
}
