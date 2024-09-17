package com.task.soaltestkerja.repository;

import com.task.soaltestkerja.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    Optional<Book> findByCode(String code);

    void deleteByCode(String code);
}
