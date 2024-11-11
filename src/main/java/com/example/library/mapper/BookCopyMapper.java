package com.example.library.mapper;

import com.example.library.domain.BookCopy;
import com.example.library.domain.Title;
import com.example.library.dto.BookCopyDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookCopyMapper {

    public BookCopyDto toDto(BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getTitle().getId(),
                bookCopy.getStatus()
        );
    }

    public BookCopy toEntity(BookCopyDto bookCopyDto, Title title) {
        return new BookCopy(
                bookCopyDto.getId(),
                title,
                bookCopyDto.getStatus()
        );
    }

    public List<BookCopyDto> toDtoList(List<BookCopy> bookCopies) {
        return bookCopies.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<BookCopy> toEntityList(List<BookCopyDto> bookCopyDtos, Title title) {
        return bookCopyDtos.stream()
                .map(dto -> toEntity(dto, title))
                .collect(Collectors.toList());
    }
}
