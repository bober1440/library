package com.example.library.service;

import com.example.library.domain.BookCopy;
import com.example.library.domain.Title;
import com.example.library.repository.BookCopyRepository;
import com.example.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;
    private final TitleRepository titleRepository;

    @Autowired
    public BookCopyService(BookCopyRepository bookCopyRepository, TitleRepository titleRepository) {
        this.bookCopyRepository = bookCopyRepository;
        this.titleRepository = titleRepository;
    }


    public BookCopy saveBookCopy(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }


    public void updateStatus(Long id, String status) {
        BookCopy bookCopy = bookCopyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book copy ID: " + id));
        bookCopy.setStatus(status);
        bookCopyRepository.save(bookCopy);
    }


    public long countAvailableCopies(Long titleId) {
        Title title = titleRepository.findById(titleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid title ID: " + titleId));
        return bookCopyRepository.findByTitleAndStatus(title, "available").size();
    }


    public Optional<BookCopy> getBookCopy(Long id) {
        return bookCopyRepository.findById(id);
    }
}
