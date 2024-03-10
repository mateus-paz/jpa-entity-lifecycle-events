package com.mateuspaz.jpaentitylifecycleevents.entity.listener;

import com.mateuspaz.jpaentitylifecycleevents.entity.EntidadeDominio;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@Slf4j
public class EntidadeDominioListener {

    public void logarInfo(String action, EntidadeDominio entidadeDominio){
        log.info("{} - {}", action, entidadeDominio.getClass().getSimpleName());
    }

    @PrePersist
    public void prePersist(EntidadeDominio entidadeDominio) {
        entidadeDominio.setDataCadastro(LocalDateTime.now());
        logarInfo("PrePersist", entidadeDominio);
    }

    @PostPersist
    public void postPersist(EntidadeDominio entidadeDominio) {
        logarInfo("PostPersist", entidadeDominio);
    }

    @PreRemove
    public void preRemove(EntidadeDominio entidadeDominio) {
        logarInfo("PreRemove", entidadeDominio);
    }

    @PostRemove
    public void postRemove(EntidadeDominio entidadeDominio) {
        logarInfo("PostRemove", entidadeDominio);
    }

    @PreUpdate
    public void PreUpdate(EntidadeDominio entidadeDominio) {
        entidadeDominio.setDataAtualizacao(LocalDateTime.now());
        logarInfo("PreUpdate", entidadeDominio);
    }

    @PostUpdate
    public void PostUpdate(EntidadeDominio entidadeDominio) {
        logarInfo("PostUpdate", entidadeDominio);
    }

    @PostLoad
    public void postLoad(EntidadeDominio entidadeDominio) {
        logarInfo("PostLoad", entidadeDominio);
    }

}
