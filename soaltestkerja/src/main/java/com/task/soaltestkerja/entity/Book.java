package com.task.soaltestkerja.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Setter @Getter  @NoArgsConstructor
public class Book {

    @Id
    private String code;

    private String title;

    private String author;

    private Integer stock;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Borrow> borrows;

    public Book(String code, String title, String author, Integer stock) {
        this.code = code;
        this.title = title;
        this.author = author;
        this.stock = stock;
    }
}
