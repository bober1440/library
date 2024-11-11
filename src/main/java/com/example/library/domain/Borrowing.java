package com.example.library.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "borrowings")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @Column(name = "borrowed_date")
    private LocalDate borrowedDate;

    @Column(name = "returned_date")
    private LocalDate returnedDate;


    public Borrowing() {
    }


    public Borrowing(Long id, BookCopy bookCopy, Reader reader, LocalDate borrowedDate, LocalDate returnedDate) {
        this.id = id;
        this.bookCopy = bookCopy;
        this.reader = reader;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Borrowing)) return false;
        Borrowing borrowing = (Borrowing) o;
        return Objects.equals(id, borrowing.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}