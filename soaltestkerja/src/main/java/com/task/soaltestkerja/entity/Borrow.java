package com.task.soaltestkerja.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;


@Entity
@Table(name = "borrows")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_code")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_code")
    private Book book;


    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate memberReturnBookDate;
    private Integer isReturn;

}
