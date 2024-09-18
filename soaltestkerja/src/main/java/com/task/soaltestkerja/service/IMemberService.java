package com.task.soaltestkerja.service;

import com.task.soaltestkerja.dto.MemberDto;

import java.util.List;

public interface IMemberService {

    List<MemberDto> getAllMember();

    MemberDto findByCode(String code);

    MemberDto create(MemberDto memberDto);

    MemberDto update(MemberDto memberDto);

    void delete(String code);
}
