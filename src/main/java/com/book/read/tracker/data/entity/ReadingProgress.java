package com.book.read.tracker.data.entity;

import com.book.read.tracker.data.bookenum.STATUS;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadingProgress {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isFinished = false;

    private Integer setNumberOfPageToReadTarget = 0;

    private Integer currentPage = 0;

    @Enumerated(value = EnumType.STRING)
    private STATUS status = STATUS.YET_TO_READ;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Book book;
}
