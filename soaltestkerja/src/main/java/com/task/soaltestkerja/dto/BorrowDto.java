package com.task.soaltestkerja.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.task.soaltestkerja.entity.Book;
import com.task.soaltestkerja.entity.Member;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BorrowDto {

    private Long id;
    private String memberCode;
    private String bookCode;

    // masih di pertimbangin
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate borrowDate;
    private LocalDate returnDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate memberReturnBookDate;
    private Integer isReturn;
}
