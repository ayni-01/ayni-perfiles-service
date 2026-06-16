package com.somosayni.perfiles.domain.repository;

import com.somosayni.perfiles.domain.model.PerfilTalento;
import java.util.List;
import java.util.Optional;

public interface PerfilTalentoRepository {
    Optional<PerfilTalento> findByUsuarioId(String usuarioId);
    Optional<PerfilTalento> findById(String id);
    PerfilTalento save(PerfilTalento perfil);
    List<PerfilTalento> findAll();
}
