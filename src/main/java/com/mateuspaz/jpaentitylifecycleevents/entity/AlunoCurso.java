package com.mateuspaz.jpaentitylifecycleevents.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoCurso extends EntidadeDominio {
    @ManyToOne
    private Aluno aluno;
    @ManyToOne
    private Curso curso;
    private boolean ativo;
}
