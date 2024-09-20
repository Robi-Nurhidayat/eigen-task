package com.task.soaltestkerja.controller;

import com.task.soaltestkerja.dto.BorrowDto;
import com.task.soaltestkerja.dto.BorrowResponse;
import com.task.soaltestkerja.dto.Response;
import com.task.soaltestkerja.service.IBorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
public class BorrowController {

    private final IBorrowService borrowService;

    @GetMapping
    public ResponseEntity<Response> getAllBorrows() {

        List<BorrowResponse> allDataBorrow = borrowService.getAllDataBorrow();
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("200","success get all data",allDataBorrow));
    }


    @PostMapping
    public ResponseEntity<String> createBorrow(@RequestBody BorrowDto borrowDto) {

        borrowService.createBorrow(borrowDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success create borrow");
    }

    @PostMapping("return")
    public ResponseEntity<Response> returnBorrow(@RequestBody BorrowDto borrowDto) {

        borrowService.returningBorrow(borrowDto);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("200","Succes return borrow",null));
    }
}
