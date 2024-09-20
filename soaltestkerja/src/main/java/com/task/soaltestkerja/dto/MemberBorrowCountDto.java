package com.task.soaltestkerja.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class MemberBorrowCountDto {

    private String memberCode;
    private String memberName;
    private long borrowCount;

}
