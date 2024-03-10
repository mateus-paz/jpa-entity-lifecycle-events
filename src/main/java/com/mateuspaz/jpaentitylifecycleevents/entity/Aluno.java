package com.mateuspaz.jpaentitylifecycleevents.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aluno extends EntidadeDominio {

    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aluno")
    @JsonIgnore
    private List<AlunoCurso> associacoes;
    @Transient
    private List<Curso> cursosAtivos;

    public Aluno(String nome) {
        this.nome = nome;
    }
    @PostLoad
    public void postLoad() {
        if(!CollectionUtils.isEmpty(associacoes))
            this.cursosAtivos = associacoes.stream().filter(AlunoCurso::isAtivo).map(AlunoCurso::getCurso).toList();
    }

    public void associarCurso(Curso curso) {
        if(CollectionUtils.isEmpty(associacoes))
            this.associacoes = new ArrayList<>();

        this.associacoes.add(new AlunoCurso(this, curso, true));
    }

    public void associarCursos(Set<Curso> cursos) {
        if(CollectionUtils.isEmpty(this.associacoes))
            this.associacoes = new ArrayList<>();

        cursos.forEach(curso -> {
            boolean ativo = Math.random() < 0.5;
            this.associacoes.add(new AlunoCurso(this, curso, ativo));
        });
    }
}
