package com.task.soaltestkerja.controller;

import com.task.soaltestkerja.dto.PenaltyDto;
import com.task.soaltestkerja.dto.Response;
import com.task.soaltestkerja.entity.Penalti;
import com.task.soaltestkerja.service.IPenaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/penalties")
@RequiredArgsConstructor
public class PenaltyController {

    private final IPenaltyService penaltyService;

    @GetMapping
    public ResponseEntity<Response> getAllPenalties() {

        List<PenaltyDto> allPenalty = penaltyService.getAllPenalty();

        return ResponseEntity.status(HttpStatus.OK).body(new Response("200", "Successfully get all data", allPenalty));

    }
}
