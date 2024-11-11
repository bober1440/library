package com.example.library.service;

import com.example.library.domain.Reader;
import com.example.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReaderService {

    private final ReaderRepository readerRepository;

    // Konstruktor z wstrzyknięciem zależności (Dependency Injection)
    @Autowired
    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    // Metoda dodająca nowego czytelnika
    public Reader saveReader(Reader reader) {
        return readerRepository.save(reader);
    }

    // Metoda pobierająca czytelnika na podstawie ID
    public Optional<Reader> getReader(Long id) {
        return readerRepository.findById(id);
    }

    // Metoda pobierająca wszystkich czytelników
    public List<Reader> getAllReaders() {
        return (List<Reader>) readerRepository.findAll();
    }

    // Metoda usuwająca czytelnika na podstawie ID
    public void deleteReader(Long id) {
        readerRepository.deleteById(id);
    }
}
