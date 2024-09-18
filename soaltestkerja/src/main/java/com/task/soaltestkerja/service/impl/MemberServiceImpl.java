package com.task.soaltestkerja.service.impl;

import com.task.soaltestkerja.dto.MemberDto;
import com.task.soaltestkerja.entity.Member;
import com.task.soaltestkerja.exception.ResourceAlreadyExistsException;
import com.task.soaltestkerja.exception.ResourceNotFoundException;
import com.task.soaltestkerja.repository.MemberRepository;
import com.task.soaltestkerja.service.IMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements IMemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<MemberDto> getAllMember() {
        List<Member> members = memberRepository.findAll();
        List<MemberDto> memberDtos = members.stream().map(data -> new MemberDto(data.getCode(),data.getName())).collect(Collectors.toList());
        return memberDtos;
    }

    @Override
    public MemberDto findByCode(String code) {
        Member member = memberRepository.findById(code).orElseThrow(
                () -> new ResourceNotFoundException("Member","code",code)
        );

        MemberDto memberDto = new MemberDto();
        memberDto.setCode(member.getCode());
        memberDto.setName(member.getName());

        return memberDto;
    }

    @Override
    public MemberDto create(MemberDto memberDto) {

        Optional<Member> member = memberRepository.findById(memberDto.getCode());
        if (member.isPresent()) {
            throw new ResourceAlreadyExistsException("Member already in database");
        }

        Member newMember = new Member();
        newMember.setCode(memberDto.getCode());
        newMember.setName(memberDto.getName());
        memberRepository.save(newMember);

        return memberDto;
    }

    @Override
    public MemberDto update(MemberDto memberDto) {
        Member member = memberRepository.findById(memberDto.getCode()).orElseThrow(
                () -> new ResourceNotFoundException("Member","code", memberDto.getCode())
        );

        member.setCode(memberDto.getCode());
        member.setName(memberDto.getName());
        memberRepository.save(member);

        return memberDto;
    }

    @Override
    public void delete(String code) {

        memberRepository.findById(code).orElseThrow(
                () -> new ResourceNotFoundException("Member","code", code)
        );

        memberRepository.deleteById(code);
    }
}
