package com.example.library.repository;

import com.example.library.domain.Borrowing;
import com.example.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BorrowingRepository extends CrudRepository<Borrowing, Long> {
    List<Borrowing> findByReaderAndReturnedDateIsNull(Reader reader);
}
