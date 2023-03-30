package com.book.read.tracker.service.progress;

import com.book.read.tracker.data.bookenum.STATUS;
import com.book.read.tracker.data.dao.ReadingProgressDao;
import com.book.read.tracker.data.entity.Book;
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


    @Override
    public String setDaily_weeklyReadingTarget(Long bookId, ReadingProgressDao target) {
        Book book = utility.getBookById ( bookId );
        validateInput ( target.getSetNumberOfPageToReadTarget () );
            book.getReadingProgress ().setSetNumberOfPageToReadTarget (  target.getSetNumberOfPageToReadTarget () );
            progressRepository.save ( book.getReadingProgress () );
        return "success";
    }


    @Override
    public String setCurrentPage(Long bookId, ReadingProgressDao currentPage){
        Book book = utility.getBookById ( bookId );
        validateInput ( currentPage.getCurrentPage () );
        book.getReadingProgress ().setCurrentPage ( currentPage.getCurrentPage () );
        progressRepository.save ( book.getReadingProgress () );
        return "success";
    }

    @Override
    public String setReadStatus(Long bookId, ReadingProgressDao status){
        Book book = utility.getBookById ( bookId );
        if(status.getStatus () == null){
            throw new NullPointerException ( "status can not be null" );
        }
        if (status.getStatus ().equals ( "FINISHED" )){
            if (book.getReadingProgress ().getStatus ().equals ( STATUS.YET_TO_READ )) {
                throw new BookException ( "when did you start to read this book? because you are yet to set the status to 'IN_PROGRESS'" );
            }else {
                book.getReadingProgress ().setFinished ( true );
                book.getReadingProgress ().setStatus ( STATUS.valueOf ( status.getStatus () ) );
            }

        }else if(status.getStatus ().equals ( "YET_TO_READ")){
            book.getReadingProgress ().setFinished ( false );
            book.getReadingProgress ().setStatus ( STATUS.valueOf ( status.getStatus () ) );
        }else {
            book.getReadingProgress ().setStatus ( STATUS.valueOf (  status.getStatus () ) );
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
