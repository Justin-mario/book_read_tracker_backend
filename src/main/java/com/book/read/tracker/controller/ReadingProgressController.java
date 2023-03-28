package com.book.read.tracker.controller;

import com.book.read.tracker.service.progress.ReadingProgressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/progress")
public class ReadingProgressController {

    private final ReadingProgressService progressService;
    public ReadingProgressController(ReadingProgressService progressService) {
        this.progressService = progressService;
    }

//    @PatchMapping ("/{id}")
//    public ResponseEntity<?> IsFinished(@PathVariable Long id){
//        return new ResponseEntity<> ( progressService.isFinished ( id ), HttpStatus.OK );
//    }

    @PatchMapping ("/{id}/{target}")
    public ResponseEntity<?> setDaily_weeklyReadingTarget(@PathVariable Long id, @PathVariable int target){
        return new ResponseEntity<> ( progressService.setDaily_weeklyReadingTarget ( id, target ), HttpStatus.OK );
    }

    @PatchMapping ("set-current-page/{id}/{currentPage}")
    public ResponseEntity<?> setCurrentPage(@PathVariable Long id, @PathVariable int currentPage){
        return new ResponseEntity<> ( progressService.setCurrentPage ( id, currentPage ), HttpStatus.OK );
    }

    @PatchMapping ("set-read-status/{id}/{status}")
    public ResponseEntity<?> setReadStatus(@PathVariable Long id, @PathVariable String status){
        return new ResponseEntity<> ( progressService.setReadStatus ( id, status ), HttpStatus.OK );
    }


}
