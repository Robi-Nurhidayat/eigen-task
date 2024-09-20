package com.task.soaltestkerja.repository;

import com.task.soaltestkerja.entity.Penalti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PenaltyRepository extends JpaRepository<Penalti,Long> {

    Optional<Penalti> findByMemberCode(String memberCode);
}
