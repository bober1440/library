package com.example.library.mapper;

import com.example.library.domain.Title;
import com.example.library.dto.TitleDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TitleMapper {

    public TitleDto toDto(Title title) {
        return new TitleDto(
                title.getId(),
                title.getTitle(),
                title.getAuthor(),
                title.getYearPublished()
        );
    }

    public Title toEntity(TitleDto titleDto) {
        return new Title(
                titleDto.getId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getYearPublished()
        );
    }

    public List<TitleDto> toDtoList(List<Title> titles) {
        return titles.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Title> toEntityList(List<TitleDto> titleDtos) {
        return titleDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}