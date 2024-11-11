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


    @Autowired
    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }


    public Reader saveReader(Reader reader) {
        return readerRepository.save(reader);
    }


    public Optional<Reader> getReader(Long id) {
        return readerRepository.findById(id);
    }


    public List<Reader> getAllReaders() {
        return (List<Reader>) readerRepository.findAll();
    }


    public void deleteReader(Long id) {
        readerRepository.deleteById(id);
    }
}
