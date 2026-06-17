package com.somosayni.perfiles.application.query;

import com.somosayni.perfiles.application.port.PerfilEmpresaRepository;
import com.somosayni.perfiles.domain.model.PerfilEmpresa;
import org.springframework.stereotype.Component;

@Component
public class ObtenerPerfilEmpresaQueryHandler {

    private final PerfilEmpresaRepository repository;

    public ObtenerPerfilEmpresaQueryHandler(PerfilEmpresaRepository repository) {
        this.repository = repository;
    }

    public PerfilEmpresa handle(ObtenerPerfilEmpresaQuery query) {
        String id = query.empresaId();
        return repository.findById(id)
                .or(() -> repository.findByUsuarioId(id))
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada"));
    }
}
