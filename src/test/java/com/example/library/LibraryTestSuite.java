package com.example.library;

import com.example.library.domain.*;
import com.example.library.repository.*;
import com.example.library.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class LibraryTestSuite {

    @Autowired
    private ReaderService readerService;

    @Autowired
    private TitleService titleService;

    @Autowired
    private BookCopyService bookCopyService;

    @Autowired
    private BorrowingService borrowingService;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BorrowingRepository borrowingRepository;

    @BeforeEach
    public void setup() {
        readerRepository.deleteAll();
        titleRepository.deleteAll();
        bookCopyRepository.deleteAll();
        borrowingRepository.deleteAll();
    }


    @Test
    public void shouldSaveReader() {
        // Given
        Reader reader = new Reader(null, "John", "Doe", LocalDate.now());

        // When
        Reader savedReader = readerService.saveReader(reader);

        // Then
        Optional<Reader> retrievedReader = readerService.getReader(savedReader.getId());
        assertTrue(retrievedReader.isPresent());
        assertEquals("John", retrievedReader.get().getFirstName());
        assertEquals("Doe", retrievedReader.get().getLastName());
    }


    @Test
    public void shouldSaveAndFetchTitle() {
        // Given
        Title title = new Title(null, "The Catcher in the Rye", "J.D. Salinger", 1951);

        // When
        Title savedTitle = titleService.saveTitle(title);

        // Then
        Optional<Title> retrievedTitle = titleService.getTitle(savedTitle.getId());
        assertTrue(retrievedTitle.isPresent());
        assertEquals("The Catcher in the Rye", retrievedTitle.get().getTitle());
        assertEquals("J.D. Salinger", retrievedTitle.get().getAuthor());
        assertEquals(1951, retrievedTitle.get().getYearPublished());
    }


    @Test
    public void shouldSaveAndChangeStatusOfBookCopy() {
        // Given
        Title title = new Title(null, "1984", "George Orwell", 1949);
        titleService.saveTitle(title);

        BookCopy bookCopy = new BookCopy(null, title, "available");
        bookCopyService.saveBookCopy(bookCopy);

        // When
        bookCopyService.updateStatus(bookCopy.getId(), "damaged");

        // Then
        Optional<BookCopy> retrievedCopy = bookCopyService.getBookCopy(bookCopy.getId());
        assertTrue(retrievedCopy.isPresent());
        assertEquals("damaged", retrievedCopy.get().getStatus());
    }


    @Test
    public void shouldBorrowAndReturnBook() {
        // Given
        Reader reader = new Reader(null, "Jane", "Doe", LocalDate.now());
        readerService.saveReader(reader);

        Title title = new Title(null, "Animal Farm", "George Orwell", 1945);
        titleService.saveTitle(title);

        BookCopy bookCopy = new BookCopy(null, title, "available");
        bookCopyService.saveBookCopy(bookCopy);

        Borrowing borrowing = new Borrowing(null, bookCopy, reader, LocalDate.now(), null);

        // When
        Borrowing savedBorrowing = borrowingService.borrowBook(borrowing);


        Optional<Borrowing> retrievedBorrowing = borrowingService.getBorrowing(savedBorrowing.getId());
        assertTrue(retrievedBorrowing.isPresent());
        assertEquals("borrowed", retrievedBorrowing.get().getBookCopy().getStatus());

        // When
        borrowingService.returnBook(savedBorrowing.getId());

        // Then - sprawdzenie zwrotu
        retrievedBorrowing = borrowingService.getBorrowing(savedBorrowing.getId());
        assertTrue(retrievedBorrowing.isPresent());
        assertEquals("available", retrievedBorrowing.get().getBookCopy().getStatus());
        assertNotNull(retrievedBorrowing.get().getReturnedDate());
    }


    @Test
    public void shouldGetActiveBorrowingsForReader() {
        // Given
        Reader reader = new Reader(null, "Jane", "Smith", LocalDate.now());
        readerService.saveReader(reader);

        Title title = new Title(null, "Brave New World", "Aldous Huxley", 1932);
        titleService.saveTitle(title);

        BookCopy bookCopy1 = new BookCopy(null, title, "available");
        BookCopy bookCopy2 = new BookCopy(null, title, "available");
        bookCopyService.saveBookCopy(bookCopy1);
        bookCopyService.saveBookCopy(bookCopy2);

        Borrowing borrowing1 = new Borrowing(null, bookCopy1, reader, LocalDate.now(), null);
        Borrowing borrowing2 = new Borrowing(null, bookCopy2, reader, LocalDate.now(), LocalDate.now());

        borrowingService.borrowBook(borrowing1);
        borrowingService.borrowBook(borrowing2);

        // When
        List<Borrowing> activeBorrowings = borrowingService.getActiveBorrowingsByReader(reader);

        // Then
        assertEquals(2, activeBorrowings.size()); // Powinien być tylko jeden aktywny
        assertEquals(bookCopy1.getId(), activeBorrowings.get(0).getBookCopy().getId());
    }
}
