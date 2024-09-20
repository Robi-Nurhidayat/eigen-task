package com.task.soaltestkerja.controller;

import com.task.soaltestkerja.dto.BookDto;
import com.task.soaltestkerja.dto.MemberBorrowCountDto;
import com.task.soaltestkerja.dto.MemberDto;
import com.task.soaltestkerja.dto.Response;
import com.task.soaltestkerja.service.IMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final IMemberService memberService;

    @GetMapping
    public ResponseEntity<Response> getAllBook() {
        List<MemberBorrowCountDto> memberDtos = memberService.getAllMember();
        return ResponseEntity.status(HttpStatus.OK).body(new Response("200", "successfully get all data", memberDtos));

    }

    @GetMapping("/find")
    public ResponseEntity<Response> findBookByCode(@RequestParam("code") String code) {

        MemberDto memberDto = memberService.findByCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("200", "successfully get  data", memberDto));

    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody MemberDto memberDto) {

        MemberDto newMember = memberService.create(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("201", "successfully create data", newMember));

    }

    @PutMapping
    public ResponseEntity<Response> update(@RequestBody MemberDto memberDto) {
        MemberDto updatedMember = memberService.update(memberDto);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("200", "successfully update data", updatedMember));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> update(@RequestParam("code") String code) {
        memberService.delete(code);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("200", "successfully update data", ""));

    }

    @PostMapping("/setup")
    public ResponseEntity<String> setup() {
        memberService.setup();
        return ResponseEntity.status(HttpStatus.OK).body("Success setup");

    }
}
