package com.somosayni.perfiles.application.port;

import com.somosayni.perfiles.domain.model.PerfilEmpresa;
import java.util.List;
import java.util.Optional;

public interface PerfilEmpresaRepository {
    Optional<PerfilEmpresa> findByUsuarioId(String usuarioId);
    Optional<PerfilEmpresa> findById(String id);
    Optional<PerfilEmpresa> findByRuc(String ruc);
    PerfilEmpresa save(PerfilEmpresa perfil);
    List<PerfilEmpresa> findByEstadoValidacion(PerfilEmpresa.EstadoValidacion estado);
}
