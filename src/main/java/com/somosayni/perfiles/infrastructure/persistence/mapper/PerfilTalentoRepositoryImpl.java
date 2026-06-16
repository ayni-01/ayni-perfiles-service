package com.somosayni.perfiles.infrastructure.persistence.mapper;

import com.somosayni.perfiles.application.port.PerfilTalentoRepository;
import com.somosayni.perfiles.domain.model.PerfilTalento;
import com.somosayni.perfiles.infrastructure.persistence.entity.PerfilTalentoEntity;
import com.somosayni.perfiles.infrastructure.persistence.repository.JpaPerfilTalentoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PerfilTalentoRepositoryImpl implements PerfilTalentoRepository {

    private final JpaPerfilTalentoRepository jpaRepository;

    public PerfilTalentoRepositoryImpl(JpaPerfilTalentoRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<PerfilTalento> findAll() {
        return jpaRepository.findAll().stream()
                .map(PerfilTalentoEntity::toDomain)
                .toList();
    }

    @Override
    public PerfilTalento save(PerfilTalento perfil) {
        return jpaRepository.save(PerfilTalentoEntity.fromDomain(perfil)).toDomain();
    }

    @Override
    public Optional<PerfilTalento> findById(String id) {
        return jpaRepository.findById(id).map(PerfilTalentoEntity::toDomain);
    }

    @Override
    public Optional<PerfilTalento> findByUsuarioId(String usuarioId) {
        return jpaRepository.findByUsuarioId(usuarioId).map(PerfilTalentoEntity::toDomain);
    }
}
