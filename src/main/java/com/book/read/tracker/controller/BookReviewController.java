package com.book.read.tracker.controller;

import com.book.read.tracker.data.dao.BookReviewDao;
import com.book.read.tracker.service.review.BookReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class BookReviewController {

    private final BookReviewService bookReviewService;

    public BookReviewController(BookReviewService bookReviewService){
        this.bookReviewService = bookReviewService;
    }

    @PatchMapping("review/{id}")
    public ResponseEntity<?> addReview(@PathVariable Long id, @RequestBody BookReviewDao bookReviewDao){
       return new ResponseEntity<> ( bookReviewService.addReview ( id, bookReviewDao ), HttpStatus.OK );
    }

    @PatchMapping("add-note/{id}")
    public ResponseEntity<?> addReadingNote(@PathVariable Long id, @RequestBody BookReviewDao bookReviewDao){
        return new ResponseEntity<> ( bookReviewService.addReadingNote ( id, bookReviewDao ), HttpStatus.OK );
    }

}
