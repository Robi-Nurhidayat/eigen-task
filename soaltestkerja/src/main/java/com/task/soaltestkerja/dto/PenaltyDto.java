package com.task.soaltestkerja.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PenaltyDto {

    private Long id;
    private LocalDate penaltyStart;
    private LocalDate penaltyEnd;
    private Integer isPenalty;
    private MemberDto member;
}
