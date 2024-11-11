package com.example.library.mapper;

import com.example.library.domain.Reader;
import com.example.library.dto.ReaderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {

    public ReaderDto toDto(Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getFirstName(),
                reader.getLastName(),
                reader.getAccountCreated()
        );
    }

    public Reader toEntity(ReaderDto readerDto) {
        return new Reader(
                readerDto.getId(),
                readerDto.getFirstName(),
                readerDto.getLastName(),
                readerDto.getAccountCreated()
        );
    }

    public List<ReaderDto> toDtoList(List<Reader> readers) {
        return readers.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Reader> toEntityList(List<ReaderDto> readerDtos) {
        return readerDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}