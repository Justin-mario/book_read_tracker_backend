package com.book.read.tracker.service.progress;

public interface ReadingProgressService {

    String setDaily_weeklyReadingTarget(Long bookId, Integer target);
    String setCurrentPage(Long bookId, Integer currentPage);
    String setReadStatus(Long bookId, String status);

}
