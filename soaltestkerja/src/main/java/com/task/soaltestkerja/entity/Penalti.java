package com.task.soaltestkerja.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "penalties")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Penalti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate penaltyStart;
    private LocalDate penaltyEnd;
    private Integer isPenalty;

    @OneToOne()
    @JoinColumn(name = "member_code",referencedColumnName = "code")
    private Member member;

}
