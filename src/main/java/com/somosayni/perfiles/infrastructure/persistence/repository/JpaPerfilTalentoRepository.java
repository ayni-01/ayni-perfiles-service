package com.somosayni.perfiles.infrastructure.persistence.repository;

import com.somosayni.perfiles.infrastructure.persistence.entity.PerfilTalentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JpaPerfilTalentoRepository extends JpaRepository<PerfilTalentoEntity, String> {
    Optional<PerfilTalentoEntity> findByUsuarioId(String usuarioId);
}
