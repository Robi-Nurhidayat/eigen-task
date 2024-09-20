package com.task.soaltestkerja.service.impl;

import com.task.soaltestkerja.dto.MemberDto;
import com.task.soaltestkerja.dto.PenaltyDto;
import com.task.soaltestkerja.entity.Penalti;
import com.task.soaltestkerja.repository.PenaltyRepository;
import com.task.soaltestkerja.service.IPenaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PenaltyServiceImpl implements IPenaltyService {

    private final PenaltyRepository penaltyRepository;

    @Override
    public List<PenaltyDto> getAllPenalty() {
        List<Penalti> penaltiList = penaltyRepository.findAll();

        List<PenaltyDto> results = penaltiList.stream().map(item -> convertToDto(item)).collect(Collectors.toList());

        return results;
    }

    private PenaltyDto convertToDto(Penalti penalti) {
        PenaltyDto penaltiDto = new PenaltyDto();
        penaltiDto.setId(penalti.getId());
        penaltiDto.setPenaltyStart(penalti.getPenaltyStart());
        penaltiDto.setPenaltyEnd(penalti.getPenaltyEnd());
        penaltiDto.setIsPenalty(penalti.getIsPenalty());

        // Convert member to MemberDto
        MemberDto memberDto = new MemberDto();
        memberDto.setCode(penalti.getMember().getCode());
        memberDto.setName(penalti.getMember().getName());
        penaltiDto.setMember(memberDto);

        return penaltiDto;
    }
}
