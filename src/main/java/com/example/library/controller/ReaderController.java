package com.example.library.controller;

import com.example.library.domain.Reader;
import com.example.library.dto.ReaderDto;
import com.example.library.mapper.ReaderMapper;
import com.example.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readers")
public class ReaderController {

    private final ReaderService readerService;
    private final ReaderMapper readerMapper;

    @Autowired
    public ReaderController(ReaderService readerService, ReaderMapper readerMapper) {
        this.readerService = readerService;
        this.readerMapper = readerMapper;
    }

    @PostMapping
    public ResponseEntity<ReaderDto> addReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerService.saveReader(readerMapper.toEntity(readerDto));
        return ResponseEntity.ok(readerMapper.toDto(reader));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReaderDto> getReader(@PathVariable Long id) {
        return readerService.getReader(id)
                .map(reader -> ResponseEntity.ok(readerMapper.toDto(reader)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ReaderDto>> getAllReaders() {
        List<Reader> readers = readerService.getAllReaders();
        return ResponseEntity.ok(readerMapper.toDtoList(readers));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
        return ResponseEntity.noContent().build();
    }
}
