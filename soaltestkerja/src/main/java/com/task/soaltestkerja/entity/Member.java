package com.task.soaltestkerja.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@Setter
@Getter
@NoArgsConstructor

public class Member {

    @Id
    private String code;

    private String name;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Penalti penalti;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Borrow> borrows = new ArrayList<>();

    public Member(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
