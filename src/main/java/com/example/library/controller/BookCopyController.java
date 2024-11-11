package com.example.library.controller;

import com.example.library.domain.BookCopy;
import com.example.library.domain.Title;
import com.example.library.dto.BookCopyDto;
import com.example.library.mapper.BookCopyMapper;
import com.example.library.service.BookCopyService;
import com.example.library.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookcopies")
public class BookCopyController {

    private final BookCopyService bookCopyService;
    private final BookCopyMapper bookCopyMapper;
    private final TitleService titleService;

    @Autowired
    public BookCopyController(BookCopyService bookCopyService, BookCopyMapper bookCopyMapper, TitleService titleService) {
        this.bookCopyService = bookCopyService;
        this.bookCopyMapper = bookCopyMapper;
        this.titleService = titleService;
    }

    @PostMapping
    public ResponseEntity<BookCopyDto> addBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        Title title = titleService.getTitle(bookCopyDto.getTitleId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid title ID: " + bookCopyDto.getTitleId()));
        BookCopy bookCopy = bookCopyService.saveBookCopy(bookCopyMapper.toEntity(bookCopyDto, title));
        return ResponseEntity.ok(bookCopyMapper.toDto(bookCopy));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam String status) {
        bookCopyService.updateStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available/{titleId}")
    public ResponseEntity<Long> countAvailableCopies(@PathVariable Long titleId) {
        long availableCopies = bookCopyService.countAvailableCopies(titleId);
        return ResponseEntity.ok(availableCopies);
    }
}
