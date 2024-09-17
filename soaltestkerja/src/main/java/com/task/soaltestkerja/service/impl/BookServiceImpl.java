package com.task.soaltestkerja.service.impl;

import ch.qos.logback.core.util.StringUtil;
import com.task.soaltestkerja.dto.BookDto;
import com.task.soaltestkerja.entity.Book;
import com.task.soaltestkerja.exception.ResourceAlreadyExistsException;
import com.task.soaltestkerja.exception.ResourceNotFoundException;
import com.task.soaltestkerja.repository.BookRepository;
import com.task.soaltestkerja.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;

    @Override
    public List<BookDto> getAllBook() {

        List<Book> books = bookRepository.findAll();

        List<BookDto> bookDtos = books.stream().map(data -> new BookDto(data.getCode(),data.getTitle(),data.getAuthor(),data.getStock())).collect(Collectors.toList());

        return bookDtos;
    }

    @Override
    public BookDto findByCode(String code) {
        Book book = bookRepository.findByCode(code).orElseThrow(
                () -> new ResourceNotFoundException("Book", "code", code)
        );

        BookDto bookDto = new BookDto();
        bookDto.setCode(book.getCode());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setStock(book.getStock());
        return bookDto;
    }

    @Override
    public BookDto create(BookDto bookDto) {

       Optional<Book> findBook = bookRepository.findByCode(bookDto.getCode());

       if (findBook.isPresent()) {
           throw new ResourceAlreadyExistsException("Books is already in database");
       }

        Book book = new Book();
        book.setCode(bookDto.getCode());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setStock(bookDto.getStock());

        bookRepository.save(book);

        return bookDto;
    }

    @Override
    public BookDto update(BookDto bookDto) {
        Book findBook = bookRepository.findByCode(bookDto.getCode()).orElseThrow(
                () -> new ResourceNotFoundException("Book","code", bookDto.getCode())
        );



        findBook.setCode(bookDto.getCode());
        findBook.setTitle(bookDto.getTitle());
        findBook.setAuthor(bookDto.getAuthor());
        findBook.setStock(bookDto.getStock());
        bookRepository.save(findBook);

        return bookDto;

    }

    @Override
    public void delete(String code) {
        bookRepository.findByCode(code).orElseThrow(
                () -> new ResourceNotFoundException("Book","code", code)
        );

        bookRepository.deleteById(code);
    }
}
