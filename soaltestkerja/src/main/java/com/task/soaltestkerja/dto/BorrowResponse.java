package com.task.soaltestkerja.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BorrowResponse {

    private Long id;
    private String memberName;
    private String bookTitle;
    private String bookAuthor;

    // tanggal pinjam
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate borrowDate;

    // tanggal pengembalian
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate returnDate;

    // tanggal member mengembalikan buku
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate memberReturnBookDate;

    // status apakah sudah dikembalikan
    private Integer isReturn;
}
