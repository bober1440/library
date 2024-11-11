package com.example.library.service;

import com.example.library.domain.Title;
import com.example.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TitleService {

    private final TitleRepository titleRepository;

    @Autowired
    public TitleService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    // Dodanie nowego tytułu
    public Title saveTitle(Title title) {
        return titleRepository.save(title);
    }

    // Pobranie tytułu na podstawie ID
    public Optional<Title> getTitle(Long id) {
        return titleRepository.findById(id);
    }

    // Pobranie wszystkich tytułów
    public List<Title> getAllTitles() {
        return (List<Title>) titleRepository.findAll();
    }

    // Usunięcie tytułu na podstawie ID
    public void deleteTitle(Long id) {
        titleRepository.deleteById(id);
    }
}
