package com.book.read.tracker.controller;

import com.book.read.tracker.data.dao.BookRatingDao;
import com.book.read.tracker.service.rating.BookRatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/rating")
public class BookRatingController {

    private final BookRatingService bookRatingService;

    public BookRatingController(BookRatingService bookRatingService){
        this.bookRatingService=bookRatingService;
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<?> addRating(@PathVariable Long bookId, @RequestBody BookRatingDao bookRatingDao){
        return new ResponseEntity<> ( bookRatingService.addRating (bookId, bookRatingDao ), HttpStatus.OK );
    }
}
