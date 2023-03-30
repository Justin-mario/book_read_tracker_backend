package com.book.read.tracker.controller;

import com.book.read.tracker.data.dao.BookDao;
import com.book.read.tracker.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/book")
@EnableTransactionManagement
public class BookController {


    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService){
        this.bookService= bookService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> add(@RequestBody BookDao bookDAO){
        return new ResponseEntity<> ( bookService.addBook ( bookDAO ), HttpStatus.CREATED );
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return new ResponseEntity<> ( bookService.getById ( id ), HttpStatus.OK );
    }

    @GetMapping("/by-title/{title}")
    public ResponseEntity<?> getByTitle(@PathVariable String title){
        return new ResponseEntity<> ( bookService.getBookByTitle ( title ), HttpStatus.OK );
    }

    @GetMapping("/by-author/{author}")
    public ResponseEntity<?> getAllByAuthor(@PathVariable String author){
        return new ResponseEntity<> ( bookService.getAllBooksByAuthor ( author ), HttpStatus.OK );
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<> ( bookService.getAllBooks (), HttpStatus.OK );
    }

    @DeleteMapping("/by-title/{title}")
    public ResponseEntity<?> deleteByTitle(@PathVariable String title){
        return new ResponseEntity<> ( bookService.deleteBookByTitle ( title ), HttpStatus.OK );
    }

    @DeleteMapping("/by-author/{author}")
    public ResponseEntity<?> deleteAllByAuthor(@PathVariable String author){
        return new ResponseEntity<> ( bookService.deleteAllBooksByAuthor ( author ), HttpStatus.OK );
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll(){
        return new ResponseEntity<> ( bookService.deleteAllBooks (), HttpStatus.OK );
    }

    @GetMapping("/read")
    public ResponseEntity<?> getAllReadBooks(){
        return new ResponseEntity<> ( bookService.getAllReadBooks (), HttpStatus.OK );
    }

    @GetMapping("/yet-to-read")
    public ResponseEntity<?> getAllYetToRead(){
        return new ResponseEntity<> ( bookService.getAllYetToReadBooks (), HttpStatus.OK );
    }

    @GetMapping("/in-progress")
    public ResponseEntity<?> getInProgressBooks(){
        return new ResponseEntity<> ( bookService.getAllInProcessBook (), HttpStatus.OK );
    }


}
