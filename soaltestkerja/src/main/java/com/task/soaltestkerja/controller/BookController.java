package com.task.soaltestkerja.controller;

import com.task.soaltestkerja.dto.BookDto;
import com.task.soaltestkerja.dto.Response;
import com.task.soaltestkerja.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @GetMapping
    public ResponseEntity<Response> getAllBook() {
        List<BookDto> books = bookService.getAllBook();
        return ResponseEntity.status(HttpStatus.OK).body(new Response("200", "successfully get all data", books));

    }

    @GetMapping("/find")
    public ResponseEntity<Response> findBookByCode(@RequestParam("code") String code) {

        BookDto bookDto = bookService.findByCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("200", "successfully get  data", bookDto));

    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody BookDto bookDto) {

        BookDto book = bookService.create(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("201", "successfully create data", book));

    }

    @PutMapping
    public ResponseEntity<Response> update(@RequestBody BookDto bookDto) {
        BookDto book = bookService.update(bookDto);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("200", "successfully update data", book));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> update(@RequestParam("code") String code) {
        bookService.delete(code);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("200", "successfully update data", ""));

    }

    @PostMapping("/setup")
    public ResponseEntity<String> setup() {
        bookService.setupData();
        return ResponseEntity.status(HttpStatus.OK).body("setup success");

    }

}
