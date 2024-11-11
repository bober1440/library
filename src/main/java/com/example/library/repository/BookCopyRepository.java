package com.example.library.repository;

import com.example.library.domain.BookCopy;
import com.example.library.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {
    List<BookCopy> findByTitleAndStatus(Title title, String status);
}
