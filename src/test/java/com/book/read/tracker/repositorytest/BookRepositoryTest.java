//package com.book.read.tracker.repositorytest;
//
//import com.book.read.tracker.data.dao.BookDao;
//import com.book.read.tracker.data.entity.Book;
//import com.book.read.tracker.repository.BookRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@SpringBootTest
//@Slf4j
//public class BookRepositoryTest {
//
//    @Autowired
//    BookRepository bookRepository;
//
//
//    private List<Book> allBooks;
//    @BeforeEach
//    void setUp(){
//
//    }
//
//
//    @Test
//    @DisplayName ( "Book database is empty when no book has been added" )
//    public void bookRepositoryIsEmptyWhenNoBookAdded(){
//        allBooks = bookRepository.findAll ();
//        assertThat( allBooks ).isEmpty ();
//    }
//
//    @Test
//    @DisplayName ( "A Book can be added to book database" )
//    public void aBookCanBeAddedToBookDatabase(){
//        BookDao newBook = new BookDao ();
//        newBook.setAuthor ( "J R R Tolkien" );
//        newBook.setTitle ( "Lord Of The Rings" );
//
//        Book book = new Book (newBook);
//        bookRepository.save ( book );
//        allBooks = bookRepository.findAll ();
//        assertThat ( allBooks ).size ().isEqualTo ( 1 );
//    }
//
//    @Test
//    @DisplayName ( "multiple books can be added" )
//    public void addMultipleBooks(){
//        BookDao firstBook = new BookDao ();
//        firstBook.setAuthor ( "J R R Tolkien" );
//        firstBook.setTitle ( "Lord Of The Rings" );
//        Book book = new Book ( firstBook );
//        bookRepository.save ( book );
//
//        BookDao secondBook = new BookDao ();
//        secondBook.setAuthor ( "J R R Tolkien" );
//        secondBook.setTitle ( "The Hobbit" );
//        Book bookTwo = new Book ( secondBook );
//        bookRepository.save ( bookTwo );
//
//        allBooks = bookRepository.findAll ();
//        assertThat ( allBooks ).size ().isEqualTo ( 2 );
//    }
//
//
//    @Test
//    @DisplayName ( "Book can be deleted" )
//    public void bookCanBeDeleted(){
//        BookDao firstBook = new BookDao ();
//        firstBook.setAuthor ( "J R R Tolkien" );
//        firstBook.setTitle ( "Lord Of The Rings" );
//        Book book = new Book ( firstBook );
//        bookRepository.save ( book );
//
//        BookDao secondBook = new BookDao ();
//        secondBook.setAuthor ( "J R R Tolkien" );
//        secondBook.setTitle ( "The Hobbit" );
//        Book bookTwo = new Book ( secondBook );
//        bookRepository.save ( bookTwo );
//
//        allBooks = bookRepository.findAll ();
//        assertThat ( allBooks ).size ().isEqualTo ( 2 );
//
//        bookRepository.delete ( book );
//        List<Book> deletedBook = bookRepository.findAll ();
//        assertThat (deletedBook  ).size ().isEqualTo ( 1 );
//    }
//
//    @Test
//    @DisplayName ( "Book can be deleted by id" )
//    public void bookCanBeDeletedById(){
//        BookDao firstBook = new BookDao ();
//        firstBook.setAuthor ( "J R R Tolkien" );
//        firstBook.setTitle ( "Lord Of The Rings" );
//        Book book = new Book ( firstBook );
//        bookRepository.save ( book );
//
//        BookDao secondBook = new BookDao ();
//        secondBook.setAuthor ( "J R R Tolkien" );
//        secondBook.setTitle ( "The Hobbit" );
//        Book bookTwo = new Book ( secondBook );
//        bookRepository.save ( bookTwo );
//
//        allBooks = bookRepository.findAll ();
//        assertThat ( allBooks ).size ().isEqualTo ( 2 );
//
//        bookRepository.deleteById ( book.getId () );
//        List<Book> deletedBook = bookRepository.findAll ();
//        assertThat (deletedBook  ).size ().isEqualTo ( 1 );
//    }
//
//    @Test
//    @DisplayName ( "All Books in the book db can be deleted" )
//    public void allBooksCanBeDeleted(){
//        BookDao firstBook = new BookDao ();
//        firstBook.setAuthor ( "J R R Tolkien" );
//        firstBook.setTitle ( "Lord Of The Rings" );
//        Book book = new Book ( firstBook );
//        bookRepository.save ( book );
//
//        BookDao secondBook = new BookDao ();
//        secondBook.setAuthor ( "J R R Tolkien" );
//        secondBook.setTitle ( "The Hobbit" );
//        Book bookTwo = new Book ( secondBook );
//        bookRepository.save ( bookTwo );
//
//        allBooks = bookRepository.findAll ();
//        assertThat ( allBooks ).size ().isEqualTo ( 2 );
//
//        bookRepository.deleteAll ();
//        List<Book> deletedBooks = bookRepository.findAll ();
//        assertThat ( deletedBooks ).size ().isEqualTo ( 0 );
//    }
//
//
//    @Test
//    @DisplayName ( "find a book by id" )
//    public void findBookById(){
//        BookDao firstBook = new BookDao ();
//        firstBook.setId ( 1L );
//        firstBook.setAuthor ( "J R R Tolkien" );
//        firstBook.setTitle ( "Lord Of The Rings" );
//        Book book = new Book ( firstBook );
//        bookRepository.save ( book );
//
//        BookDao secondBook = new BookDao ();
//        secondBook.setAuthor ( "J R R Tolkien" );
//        secondBook.setTitle ( "The Hobbit" );
//        Book bookTwo = new Book ( secondBook );
//        bookRepository.save ( bookTwo );
//
//        Optional<Book> bookFound = bookRepository.findById ( 1L );;
//        assertThat ( bookFound.get ().getId () ).isEqualTo ( 1 );
//    }
//
//    @Test
//    @DisplayName ( "find a book by id" )
//    public void findBookByTitle(){
//        BookDao firstBook = new BookDao ();
//        firstBook.setId ( 1L );
//        firstBook.setAuthor ( "J R R Tolkien" );
//        firstBook.setTitle ( "Lord Of The Rings" );
//        Book book = new Book ( firstBook );
//        bookRepository.save ( book );
//
//        BookDao secondBook = new BookDao ();
//        secondBook.setAuthor ( "J R R Tolkien" );
//        secondBook.setTitle ( "The Hobbit" );
//        Book bookTwo = new Book ( secondBook );
//        bookRepository.save ( bookTwo );
//
//        Optional<Book> bookFound = bookRepository.findByTitle ( "The Hobbit" );
//        log.info ( "book found --> {}", bookFound );
//        assertThat ( bookFound.get ().getTitle () ).isEqualTo ( "The Hobbit" );
//    }
//
//    @Test
//    @DisplayName ( "find a book by id" )
//    public void findBooksByAuthor(){
//        BookDao firstBook = new BookDao ();
////        firstBook.setId ( 1L );
//        firstBook.setAuthor ( "J R R Tolkien" );
//        firstBook.setTitle ( "Lord Of The Rings" );
//        Book book = new Book ( firstBook );
//        bookRepository.save ( book );
//
//        BookDao secondBook = new BookDao ();
////        secondBook.setId ( 2L );
//        secondBook.setAuthor ( "J R R Tolkien" );
//        secondBook.setTitle ( "The Hobbit" );
//        Book bookTwo = new Book ( secondBook );
//        bookRepository.save ( bookTwo );
//
//        List<Book> bookFound = bookRepository.findAllByAuthor ( "J R R Tolkien"  );
//        assertThat ( bookFound ).size ().isEqualTo ( 2 );
//    }
//
//
//    @Test
//    @DisplayName ( "Find all the books in the book db" )
//    public void findAllBooksInBookDB(){
//        BookDao firstBook = new BookDao ();
//        firstBook.setId ( 1L );
//        firstBook.setAuthor ( "J R R Tolkien" );
//        firstBook.setTitle ( "Lord Of The Rings" );
//        Book book = new Book ( firstBook );
//        bookRepository.save ( book );
//
//        BookDao secondBook = new BookDao ();
//        secondBook.setAuthor ( "J R R Tolkien" );
//        secondBook.setTitle ( "The Hobbit" );
//        Book bookTwo = new Book ( secondBook );
//        bookRepository.save ( bookTwo );
//
//        allBooks = bookRepository.findAll ();
//        assertThat ( allBooks ).size ().isEqualTo ( 2 );
//    }
//
//    @AfterEach
//    void tearDown(){
//        bookRepository.deleteAll ();
//    }
//
//
//
//}

package com.book.read.tracker.repositorytest;

        import com.book.read.tracker.data.dao.BookDao;
        import com.book.read.tracker.data.entity.Book;
        import com.book.read.tracker.data.entity.ReadingProgress;
        import com.book.read.tracker.repository.BookRepository;
        import com.book.read.tracker.repository.ReadingProgressRepository;
        import jakarta.transaction.Transactional;
        import lombok.extern.slf4j.Slf4j;
        import org.junit.jupiter.api.*;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;

        import java.util.List;
        import java.util.Optional;

        import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ReadingProgressRepository progressRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll ();
    }

    @Test
    @DisplayName("Book database is empty when no book has been added")
    public void bookRepositoryIsEmptyWhenNoBookAdded() {
        List<Book> allBooks = bookRepository.findAll ();
        assertThat ( allBooks ).isEmpty ();
    }

    @Test
    @DisplayName("A Book can be added to book database")
    public void aBookCanBeAddedToBookDatabase() {
        BookDao newBook = new BookDao ();
        newBook.setAuthor ( "J R R Tolkien" );
        newBook.setTitle ( "Lord Of The Rings" );

        Book book = new Book ( newBook );
        bookRepository.save ( book );
        List<Book> allBooks = bookRepository.findAll ();
        assertThat ( allBooks ).size ().isEqualTo ( 1 );
    }

    @Test
    @DisplayName("Multiple books can be added")
    public void addMultipleBooks() {
        BookDao firstBook = new BookDao ();
        firstBook.setAuthor ( "J R R Tolkien" );
        firstBook.setTitle ( "Lord Of The Rings" );
        Book book = new Book ( firstBook );
        bookRepository.save ( book );

        BookDao secondBook = new BookDao ();
        secondBook.setAuthor ( "J R R Tolkien" );
        secondBook.setTitle ( "The Hobbit" );
        Book bookTwo = new Book ( secondBook );
        bookRepository.save ( bookTwo );

        List<Book> allBooks = bookRepository.findAll ();
        assertThat ( allBooks ).size ().isEqualTo ( 2 );
    }

    @Test
    @DisplayName("Book can be deleted")
    public void bookCanBeDeleted() {
        BookDao firstBook = new BookDao ();
        firstBook.setAuthor ( "J R R Tolkien" );
        firstBook.setTitle ( "Lord Of The Rings" );
        Book book = new Book ( firstBook );
        bookRepository.save ( book );

        BookDao secondBook = new BookDao ();
        secondBook.setAuthor ( "J R R Tolkien" );
        secondBook.setTitle ( "The Hobbit" );
        Book bookTwo = new Book ( secondBook );
        bookRepository.save ( bookTwo );

        List<Book> allBooks = bookRepository.findAll ();
        assertThat ( allBooks ).size ().isEqualTo ( 2 );

        bookRepository.delete ( book );
        List<Book> deletedBook = bookRepository.findAll ();
        assertThat ( deletedBook ).size ().isEqualTo ( 1 );
        assertThat ( deletedBook.get ( 0 ).getId () ).isEqualTo ( bookTwo.getId () );
    }

    @Test
    @DisplayName("Book can be deleted by id")
    public void bookCanBeDeletedById() {
        BookDao firstBook = new BookDao ();
        firstBook.setAuthor ( "J R R Tolkien" );
        firstBook.setTitle ( "Lord Of The Rings" );
        Book book = new Book ( firstBook );
        bookRepository.save ( book );

        BookDao secondBook = new BookDao ();
        secondBook.setAuthor ( "J R R Tolkien" );
        secondBook.setTitle ( "The Hobbit" );
        Book bookTwo = new Book ( secondBook );
        bookRepository.save ( bookTwo );

        List<Book> allBooks = bookRepository.findAll ();
        assertThat ( allBooks ).size ().isEqualTo ( 2 );

    }

    @Test
    @DisplayName("find a book by id")
    @Transactional
    public void findBookById() {
        // create and save two books
        BookDao firstBook = new BookDao();
        firstBook.setId(1L);
        firstBook.setAuthor("J R R Tolkien");
        firstBook.setTitle("Lord Of The Rings");
        Book book = new Book(firstBook);
        bookRepository.save(book);

        BookDao secondBook = new BookDao();
        secondBook.setId(2L);
        secondBook.setAuthor("J R R Tolkien");
        secondBook.setTitle("The Hobbit");
        Book bookTwo = new Book(secondBook);
        bookRepository.save(bookTwo);

        // find book with id 1 and assert that it has been found
        Optional<Book> bookFound = bookRepository.findById(1L);
        assertThat(bookFound).isPresent();
        assertThat(bookFound.get().getId()).isEqualTo(1);
    }


    @Test
    @DisplayName("find a book by title")
    public void findBookByTitle() {
        // create and save two books
        BookDao firstBook = new BookDao();
        firstBook.setId(1L);
        firstBook.setAuthor("J R R Tolkien");
        firstBook.setTitle("Lord Of The Rings");
        Book book = new Book(firstBook);
        bookRepository.save(book);

        BookDao secondBook = new BookDao();
        secondBook.setId(2L);
        secondBook.setAuthor("J R R Tolkien");
        secondBook.setTitle("The Hobbit");
        Book bookTwo = new Book(secondBook);
        bookRepository.save(bookTwo);

        // find book with title "The Hobbit" and assert that it has been found
        Optional<Book> bookFound = bookRepository.findByTitle("The Hobbit");
        log.info("book found --> {}", bookFound);
        assertThat(bookFound.get().getTitle()).isEqualTo("The Hobbit");
    }

    @Test
    @DisplayName("find books by author")
    public void findBooksByAuthor() {
        // create and save two books with same author
        BookDao firstBook = new BookDao();
        firstBook.setId(1L);
        firstBook.setAuthor("J R R Tolkien");
        firstBook.setTitle("Lord Of The Rings");
        Book book = new Book(firstBook);
        bookRepository.save(book);

        BookDao secondBook = new BookDao();
        secondBook.setId(2L);
        secondBook.setAuthor("J R R Tolkien");
        secondBook.setTitle("The Hobbit");
        Book bookTwo = new Book(secondBook);
        bookRepository.save(bookTwo);

        // find all books by author "J R R Tolkien" and assert that two books have been found
        List<Book> bookFound = bookRepository.findAllByAuthor("J R R Tolkien");
        assertThat(bookFound).size().isEqualTo(2);
    }


    @Test
    @DisplayName("find all the books in the book db")
    public void findAllBooksInBookDB() {
        // create and save two books
        BookDao firstBook = new BookDao();
        firstBook.setId(1L);
        firstBook.setAuthor("J R R Tolkien");
        firstBook.setTitle("Lord Of The Rings");
        Book book = new Book(firstBook);
        bookRepository.save(book);

        BookDao secondBook = new BookDao();
        secondBook.setAuthor("J R R Tolkien");
        secondBook.setTitle("The Hobbit");
        Book bookTwo = new Book(secondBook);
        bookRepository.save(bookTwo);

        // find all books in the book repository and assert that two books have been found
        List<Book> allBooks = bookRepository.findAll();
        assertThat(allBooks).size().isEqualTo(2);
    }

}