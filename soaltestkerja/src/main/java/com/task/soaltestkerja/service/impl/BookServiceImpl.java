package com.task.soaltestkerja.service.impl;

import ch.qos.logback.core.util.StringUtil;
import com.task.soaltestkerja.dto.BookDto;
import com.task.soaltestkerja.entity.Book;
import com.task.soaltestkerja.entity.Borrow;
import com.task.soaltestkerja.exception.ResourceAlreadyExistsException;
import com.task.soaltestkerja.exception.ResourceNotFoundException;
import com.task.soaltestkerja.repository.BookRepository;
import com.task.soaltestkerja.repository.BorrowRepository;
import com.task.soaltestkerja.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {


    private final BookRepository bookRepository;
    private final BorrowRepository borrowRepository;

    @Override
    public List<BookDto> getAllBook() {

        List<Book> books = bookRepository.findAll();

        List<BookDto> filterBooks = new ArrayList<>();
        for (var data: books) {

            Optional<Borrow> byBookCode = borrowRepository.findByBookCode(data.getCode());
            if (!byBookCode.isPresent()) {
                filterBooks.add(new BookDto(data.getCode(),data.getTitle(),data.getAuthor(),data.getStock()));
            }



        }


//        List<BookDto> bookDtos = books.stream().map(data -> new BookDto(data.getCode(),data.getTitle(),data.getAuthor(),data.getStock())).collect(Collectors.toList());

        return filterBooks;
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

    @Override
    public void setupData() {

        Book book1 = new Book("JK-45","Harry Potter","J.K Rowling",1);
        Book book2 = new Book("SHR-1","A Study in Scarlet","Arthur Conan Doyle",1);
        Book book3 = new Book("TW-11","Twilight","Stephenie Meyer",1);
        Book book4 = new Book("HOB-83","The Hobbit, or There and Back Again","J.R.R. Tolkien",1);
        Book book5 = new Book("NRN-7","The Lion, the Witch and the Wardrobe","C.S. Lewis",1);
        bookRepository.saveAll(List.of(book1, book2, book3, book4, book5));
    }
}
