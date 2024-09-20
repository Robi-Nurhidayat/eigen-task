package com.task.soaltestkerja.repository;

import com.task.soaltestkerja.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow,Long> {

    List<Borrow> findByMemberCode(String memberCode);
    Optional<Borrow> findByBookCode(String bookCode);
}
