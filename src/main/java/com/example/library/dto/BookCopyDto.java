package com.example.library.dto;

public class BookCopyDto {
    private Long id;
    private Long titleId;
    private String status;


    public BookCopyDto() {
    }


    public BookCopyDto(Long id, Long titleId, String status) {
        this.id = id;
        this.titleId = titleId;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}