package com.example.library.controller;

import com.example.library.domain.Title;
import com.example.library.dto.TitleDto;
import com.example.library.mapper.TitleMapper;
import com.example.library.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/titles")
public class TitleController {

    private final TitleService titleService;
    private final TitleMapper titleMapper;

    @Autowired
    public TitleController(TitleService titleService, TitleMapper titleMapper) {
        this.titleService = titleService;
        this.titleMapper = titleMapper;
    }

    @PostMapping
    public ResponseEntity<TitleDto> addTitle(@RequestBody TitleDto titleDto) {
        Title title = titleService.saveTitle(titleMapper.toEntity(titleDto));
        return ResponseEntity.ok(titleMapper.toDto(title));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TitleDto> getTitle(@PathVariable Long id) {
        return titleService.getTitle(id)
                .map(title -> ResponseEntity.ok(titleMapper.toDto(title)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TitleDto>> getAllTitles() {
        List<Title> titles = titleService.getAllTitles();
        return ResponseEntity.ok(titleMapper.toDtoList(titles));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTitle(@PathVariable Long id) {
        titleService.deleteTitle(id);
        return ResponseEntity.noContent().build();
    }
}
