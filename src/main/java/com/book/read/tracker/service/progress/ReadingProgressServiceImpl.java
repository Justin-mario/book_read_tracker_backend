package com.book.read.tracker.service.progress;

import com.book.read.tracker.data.bookenum.STATUS;
import com.book.read.tracker.data.entity.Book;
import com.book.read.tracker.data.entity.ReadingProgress;
import com.book.read.tracker.exception.BookException;
import com.book.read.tracker.repository.ReadingProgressRepository;
import com.book.read.tracker.utility.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReadingProgressServiceImpl implements ReadingProgressService{

    private final  ReadingProgressRepository progressRepository;

    private final Utility utility;

    public ReadingProgressServiceImpl(ReadingProgressRepository progressRepository, Utility utility){
       this.progressRepository = progressRepository;
       this.utility = utility;
    }

//    @Override
//    public boolean isFinished(Long bookId) {
//        Book book = utility.getBookById ( bookId );
//        boolean isFinished = book.getReadingProgress ().isFinished ();
//        if ()
//        book.getReadingProgress ().setFinished ( !isFinished );
//        progressRepository.save ( book.getReadingProgress () );
//        return book.getReadingProgress ().isFinished ();
//    }


    @Override
    public String setDaily_weeklyReadingTarget(Long bookId, Integer target) {
        Book book = utility.getBookById ( bookId );
        validateInput ( target );
            book.getReadingProgress ().setSetNumberOfPageToReadTarget ( target );
            progressRepository.save ( book.getReadingProgress () );
        return "success";
    }

    @Override
    public String setCurrentPage(Long bookId, Integer currentPage){
        Book book = utility.getBookById ( bookId );
        validateInput ( currentPage );
        book.getReadingProgress ().setCurrentPage ( currentPage );
        progressRepository.save ( book.getReadingProgress () );
        return "success";
    }

    @Override
    public String setReadStatus(Long bookId, String status){
        Book book = utility.getBookById ( bookId );
        if (status.equals ( "FINISHED" )){
            book.getReadingProgress ().setFinished ( true );
            book.getReadingProgress ().setStatus ( STATUS.valueOf ( status ) );
        }else if(status.equals ( "YET_TO_READ")){
            book.getReadingProgress ().setFinished ( false );
            book.getReadingProgress ().setStatus ( STATUS.valueOf ( status ) );
        }else {
            book.getReadingProgress ().setStatus ( STATUS.valueOf ( status ) );
        }
        progressRepository.save ( book.getReadingProgress () );
        return "success";
    }

    private void validateInput(Integer input){
        if (input < 0 ){
            throw new BookException ("number must be greater than zero");
        }
    }
}
