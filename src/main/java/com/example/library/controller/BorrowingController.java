package com.example.library.controller;

import com.example.library.domain.Borrowing;
import com.example.library.domain.BookCopy;
import com.example.library.domain.Reader;
import com.example.library.dto.BorrowingDto;
import com.example.library.mapper.BorrowingMapper;
import com.example.library.service.BorrowingService;
import com.example.library.service.BookCopyService;
import com.example.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowings")
public class BorrowingController {

    private final BorrowingService borrowingService;
    private final BorrowingMapper borrowingMapper;
    private final BookCopyService bookCopyService;
    private final ReaderService readerService;

    @Autowired
    public BorrowingController(BorrowingService borrowingService, BorrowingMapper borrowingMapper,
                               BookCopyService bookCopyService, ReaderService readerService) {
        this.borrowingService = borrowingService;
        this.borrowingMapper = borrowingMapper;
        this.bookCopyService = bookCopyService;
        this.readerService = readerService;
    }

    @PostMapping
    public ResponseEntity<BorrowingDto> borrowBook(@RequestBody BorrowingDto borrowingDto) {
        BookCopy bookCopy = bookCopyService.getBookCopy(borrowingDto.getBookCopyId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid book copy ID: " + borrowingDto.getBookCopyId()));
        Reader reader = readerService.getReader(borrowingDto.getReaderId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid reader ID: " + borrowingDto.getReaderId()));

        Borrowing borrowing = borrowingService.borrowBook(borrowingMapper.toEntity(borrowingDto, bookCopy, reader));
        return ResponseEntity.ok(borrowingMapper.toDto(borrowing));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Void> returnBook(@PathVariable Long id) {
        borrowingService.returnBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/active/{readerId}")
    public ResponseEntity<List<BorrowingDto>> getActiveBorrowingsByReader(@PathVariable Long readerId) {
        Reader reader = readerService.getReader(readerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reader ID: " + readerId));
        List<Borrowing> activeBorrowings = borrowingService.getActiveBorrowingsByReader(reader);
        return ResponseEntity.ok(borrowingMapper.toDtoList(activeBorrowings));
    }
}
