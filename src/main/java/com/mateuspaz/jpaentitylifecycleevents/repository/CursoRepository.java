package com.mateuspaz.jpaentitylifecycleevents.repository;

import com.mateuspaz.jpaentitylifecycleevents.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByTitulo(String titulo);
}
