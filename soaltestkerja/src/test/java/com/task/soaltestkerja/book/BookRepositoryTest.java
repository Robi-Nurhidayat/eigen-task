package com.task.soaltestkerja.book;

import com.task.soaltestkerja.entity.Book;
import com.task.soaltestkerja.entity.Penalti;
import com.task.soaltestkerja.repository.BookRepository;
import com.task.soaltestkerja.repository.PenaltyRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
@DataJpaTest
public class BookRepositoryTest {

    private final BookRepository bookRepository;
    private final PenaltyRepository penaltyRepository;

    @Test
    public void testFindAll() {

        Book book1 = new Book("JK-45","Harry Potter","J.K Rowling",1);
        Book book2 = new Book("SHR-1","A Study in Scarlet","Arthur Conan Doyle",1);
        Book book3 = new Book("TW-11","Twilight","Stephenie Meyer",1);
        Book book4 = new Book("HOB-83","The Hobbit, or There and Back Again","J.R.R. Tolkien",1);
        Book book5 = new Book("NRN-7","The Lion, the Witch and the Wardrobe","C.S. Lewis",1);

        List<Book> books = bookRepository.saveAll(List.of(book1, book2, book3, book4, book5));

        Assertions.assertEquals(5, books.size());
        Assertions.assertTrue(books.contains(book1));
        Assertions.assertTrue(books.contains(book2));


    }
}
