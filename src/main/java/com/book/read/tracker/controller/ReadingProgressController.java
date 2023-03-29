package com.book.read.tracker.controller;

import com.book.read.tracker.data.dao.ReadingProgressDao;
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



    @PatchMapping ("/{id}")
    public ResponseEntity<?> setDaily_weeklyReadingTarget(@PathVariable Long id, @RequestBody ReadingProgressDao target){
        return new ResponseEntity<> ( progressService.setDaily_weeklyReadingTarget ( id, target ), HttpStatus.OK );
    }

    @PatchMapping ("set-current-page/{id}")
    public ResponseEntity<?> setCurrentPage(@PathVariable Long id, @RequestBody ReadingProgressDao currentPage){
        return new ResponseEntity<> ( progressService.setCurrentPage ( id, currentPage ), HttpStatus.OK );
    }

    @PatchMapping ("set-read-status/{id}")
    public ResponseEntity<?> setReadStatus(@PathVariable Long id, @RequestBody ReadingProgressDao status){
        return new ResponseEntity<> ( progressService.setReadStatus ( id, status ), HttpStatus.OK );
    }


}
