package com.mateuspaz.jpaentitylifecycleevents.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso extends EntidadeDominio {
    private String titulo;
}
