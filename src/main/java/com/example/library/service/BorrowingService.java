package com.example.library.service;

import com.example.library.domain.Borrowing;
import com.example.library.domain.BookCopy;
import com.example.library.domain.Reader;
import com.example.library.repository.BorrowingRepository;
import com.example.library.repository.BookCopyRepository;
import com.example.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final BookCopyRepository bookCopyRepository;
    private final ReaderRepository readerRepository;

    @Autowired
    public BorrowingService(BorrowingRepository borrowingRepository,
                            BookCopyRepository bookCopyRepository,
                            ReaderRepository readerRepository) {
        this.borrowingRepository = borrowingRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.readerRepository = readerRepository;
    }

    // Wypożyczenie książki
    public Borrowing borrowBook(Borrowing borrowing) {
        BookCopy bookCopy = borrowing.getBookCopy();
        if (!"available".equals(bookCopy.getStatus())) {
            throw new IllegalStateException("BookCopy is not available for borrowing.");
        }
        bookCopy.setStatus("borrowed");
        borrowing.setBorrowedDate(LocalDate.now());
        borrowing.setReturnedDate(null);
        bookCopyRepository.save(bookCopy);
        return borrowingRepository.save(borrowing);
    }

    // Zwrot książki
    public void returnBook(Long id) {
        Borrowing borrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid borrowing ID: " + id));

        BookCopy bookCopy = borrowing.getBookCopy();
        bookCopy.setStatus("available");
        borrowing.setReturnedDate(LocalDate.now());
        bookCopyRepository.save(bookCopy);
        borrowingRepository.save(borrowing);
    }

    // Pobranie wypożyczenia na podstawie ID
    public Optional<Borrowing> getBorrowing(Long id) {
        return borrowingRepository.findById(id);
    }

    // Pobranie wszystkich wypożyczeń dla danego czytelnika
    public List<Borrowing> getActiveBorrowingsByReader(Reader reader) {
        return borrowingRepository.findByReaderAndReturnedDateIsNull(reader);
    }
}
