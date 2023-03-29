package com.book.read.tracker.service.progress;

import com.book.read.tracker.data.dao.ReadingProgressDao;

public interface ReadingProgressService {



    String setDaily_weeklyReadingTarget(Long bookId, ReadingProgressDao readingProgressDao);
    String setCurrentPage(Long bookId, ReadingProgressDao readingProgressDao);
    String setReadStatus(Long bookId, ReadingProgressDao readingProgressDao);

}
