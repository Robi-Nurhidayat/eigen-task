package com.task.soaltestkerja.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Book {

    @Id
    private String code;

    private String title;

    private String author;

    private Integer stock;
}
