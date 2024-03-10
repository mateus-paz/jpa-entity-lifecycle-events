package com.mateuspaz.jpaentitylifecycleevents.entity;

import com.mateuspaz.jpaentitylifecycleevents.entity.listener.EntidadeDominioListener;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners({EntidadeDominioListener.class})
public class EntidadeDominio {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;

}
