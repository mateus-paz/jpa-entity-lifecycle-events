package com.mateuspaz.jpaentitylifecycleevents.repository;

import com.mateuspaz.jpaentitylifecycleevents.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
