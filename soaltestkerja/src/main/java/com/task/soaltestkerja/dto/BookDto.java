package com.task.soaltestkerja.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BookDto {

    private String code;

    private String title;

    private String author;

    private Integer stock;
}
