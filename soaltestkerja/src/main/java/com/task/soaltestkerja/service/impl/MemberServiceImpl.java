package com.task.soaltestkerja.service.impl;

import com.task.soaltestkerja.dto.MemberBorrowCountDto;
import com.task.soaltestkerja.dto.MemberDto;
import com.task.soaltestkerja.entity.Borrow;
import com.task.soaltestkerja.entity.Member;
import com.task.soaltestkerja.entity.Penalti;
import com.task.soaltestkerja.exception.ResourceAlreadyExistsException;
import com.task.soaltestkerja.exception.ResourceNotFoundException;
import com.task.soaltestkerja.repository.BorrowRepository;
import com.task.soaltestkerja.repository.MemberRepository;
import com.task.soaltestkerja.repository.PenaltyRepository;
import com.task.soaltestkerja.service.IMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements IMemberService {

    private final MemberRepository memberRepository;
    private final PenaltyRepository penaltyRepository;
    private final BorrowRepository borrowRepository;

    @Override
    public List<MemberBorrowCountDto> getAllMember() {
        List<Member> members = memberRepository.findAll();
        List<MemberBorrowCountDto> memberWithCountBook = new ArrayList<>();


        for (var data: members) {

            List<Borrow> byMemberCode = borrowRepository.findByMemberCode(data.getCode());
            if (byMemberCode.size() == 2) {

                memberWithCountBook.add(new MemberBorrowCountDto(data.getCode(), data.getName(), 2));
            }else if (byMemberCode.size() == 1) {

                memberWithCountBook.add(new MemberBorrowCountDto(data.getCode(), data.getName(), 1));
            }else {
                memberWithCountBook.add(new MemberBorrowCountDto(data.getCode(), data.getName(), 0));
            }

        }

//        List<MemberDto> memberDtos = members.stream().map(data -> new MemberDto(data.getCode(),data.getName())).collect(Collectors.toList());
        return memberWithCountBook;
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

        // add content to penalty table
        Penalti penalty = new Penalti();
        penalty.setMember(newMember);
        penalty.setIsPenalty(0);
        penaltyRepository.save(penalty);
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

    @Override
    public void setup() {

        Member member1 = new Member("M001","Angga");
        Member member2 = new Member("M002","Ferry");
        Member member3 = new Member("M003","Putri");

        List<Member> members = memberRepository.saveAll(List.of(member1, member2, member3));

        List<Penalti> penaltiList = new ArrayList<>();
        for (var item: members) {
            Penalti penalti = new Penalti();
            penalti.setMember(item);
            penalti.setIsPenalty(0);

            penaltiList.add(penalti);
        }


        penaltyRepository.saveAll(penaltiList);
//        Penalti penalti1 = new Penalti();
//        penalti1.setMember(member1);
//        penalti1.setIsPenalty(0);
//
//        Penalti penalti2 = new Penalti();
//        penalti1.setMember(member2);
//        penalti1.setIsPenalty(0);
//
//        Penalti penalti3 = new Penalti();
//        penalti1.setMember(member3);
//        penalti1.setIsPenalty(0);


    }
}
