package com.task.soaltestkerja.book;


import com.task.soaltestkerja.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

//    List<BookDto> getAllBook();
//
//    BookDto findByCode(String code);
//
//    BookDto create(BookDto bookDto);
//
//    BookDto update(BookDto bookDto);
//
//    void delete(String code);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createBook() throws Exception {

    }
}
