package com.somosayni.perfiles.infrastructure.persistence.repository;

import com.somosayni.perfiles.domain.model.PerfilEmpresa;
import com.somosayni.perfiles.infrastructure.persistence.entity.PerfilEmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaPerfilEmpresaRepository extends JpaRepository<PerfilEmpresaEntity, String> {
    Optional<PerfilEmpresaEntity> findByUsuarioId(String usuarioId);
    Optional<PerfilEmpresaEntity> findByRuc(String ruc);
    List<PerfilEmpresaEntity> findByEstadoValidacion(PerfilEmpresa.EstadoValidacion estado);
}
