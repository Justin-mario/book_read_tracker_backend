package com.book.read.tracker.data.dao;

import com.book.read.tracker.data.entity.ReadingProgress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadingProgressDao {

    private Long id;

    private Integer setNumberOfPageToReadTarget;

    private Integer currentPage;

    private String status;

    public ReadingProgressDao(ReadingProgress readingProgress){
        id = readingProgress.getId ();
        setNumberOfPageToReadTarget = readingProgress.getSetNumberOfPageToReadTarget ();
        currentPage = readingProgress.getCurrentPage ();
        status = String.valueOf ( readingProgress.getStatus () );
    }
}
