package com.example.library.dto;

import java.time.LocalDate;

public class BorrowingDto {
    private Long id;
    private Long bookCopyId;
    private Long readerId;
    private LocalDate borrowedDate;
    private LocalDate returnedDate;


    public BorrowingDto() {
    }


    public BorrowingDto(Long id, Long bookCopyId, Long readerId, LocalDate borrowedDate, LocalDate returnedDate) {
        this.id = id;
        this.bookCopyId = bookCopyId;
        this.readerId = readerId;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(Long bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }
}
