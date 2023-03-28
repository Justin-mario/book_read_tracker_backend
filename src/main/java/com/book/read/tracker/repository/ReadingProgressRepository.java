package com.book.read.tracker.repository;

import com.book.read.tracker.data.entity.ReadingProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingProgressRepository extends JpaRepository<ReadingProgress, Long> {
}
