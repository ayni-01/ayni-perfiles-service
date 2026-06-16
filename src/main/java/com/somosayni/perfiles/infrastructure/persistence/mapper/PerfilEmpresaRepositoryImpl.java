package com.somosayni.perfiles.infrastructure.persistence.mapper;

import com.somosayni.perfiles.application.port.PerfilEmpresaRepository;
import com.somosayni.perfiles.domain.model.PerfilEmpresa;
import com.somosayni.perfiles.infrastructure.persistence.entity.PerfilEmpresaEntity;
import com.somosayni.perfiles.infrastructure.persistence.repository.JpaPerfilEmpresaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class PerfilEmpresaRepositoryImpl implements PerfilEmpresaRepository {

    private final JpaPerfilEmpresaRepository jpaRepository;

    public PerfilEmpresaRepositoryImpl(JpaPerfilEmpresaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<PerfilEmpresa> findByEstadoValidacion(PerfilEmpresa.EstadoValidacion estado) {
        return jpaRepository.findByEstadoValidacion(estado).stream()
                .map(PerfilEmpresaEntity::toDomain)
                .toList();
    }

    @Override
    public PerfilEmpresa save(PerfilEmpresa perfil) {
        return jpaRepository.save(PerfilEmpresaEntity.fromDomain(perfil)).toDomain();
    }

    @Override
    public Optional<PerfilEmpresa> findById(String id) {
        return jpaRepository.findById(id).map(PerfilEmpresaEntity::toDomain);
    }

    @Override
    public Optional<PerfilEmpresa> findByRuc(String ruc) {
        return jpaRepository.findByRuc(ruc).map(PerfilEmpresaEntity::toDomain);
    }

    @Override
    public Optional<PerfilEmpresa> findByUsuarioId(String usuarioId) {
        return jpaRepository.findByUsuarioId(usuarioId).map(PerfilEmpresaEntity::toDomain);
    }
}
