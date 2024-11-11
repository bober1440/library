package com.example.library.mapper;

import com.example.library.domain.Borrowing;
import com.example.library.domain.BookCopy;
import com.example.library.domain.Reader;
import com.example.library.dto.BorrowingDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BorrowingMapper {

    public BorrowingDto toDto(Borrowing borrowing) {
        return new BorrowingDto(
                borrowing.getId(),
                borrowing.getBookCopy().getId(),
                borrowing.getReader().getId(),
                borrowing.getBorrowedDate(),
                borrowing.getReturnedDate()
        );
    }

    public Borrowing toEntity(BorrowingDto borrowingDto, BookCopy bookCopy, Reader reader) {
        return new Borrowing(
                borrowingDto.getId(),
                bookCopy,
                reader,
                borrowingDto.getBorrowedDate(),
                borrowingDto.getReturnedDate()
        );
    }

    public List<BorrowingDto> toDtoList(List<Borrowing> borrowings) {
        return borrowings.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Borrowing> toEntityList(List<BorrowingDto> borrowingDtos, BookCopy bookCopy, Reader reader) {
        return borrowingDtos.stream()
                .map(dto -> toEntity(dto, bookCopy, reader))
                .collect(Collectors.toList());
    }
}
