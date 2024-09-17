package com.task.soaltestkerja.service;

import com.task.soaltestkerja.dto.BookDto;

import java.util.List;

public interface IBookService {

    List<BookDto> getAllBook();

    BookDto findByCode(String code);

    BookDto create(BookDto bookDto);

    BookDto update(BookDto bookDto);

    void delete(String code);



}
