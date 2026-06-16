package com.somosayni.perfiles.application.query;

import com.somosayni.perfiles.application.port.PerfilTalentoRepository;
import com.somosayni.perfiles.domain.model.PerfilTalento;
import org.springframework.stereotype.Component;

@Component
public class ObtenerPerfilTalentoQueryHandler {

    private final PerfilTalentoRepository repository;

    public ObtenerPerfilTalentoQueryHandler(PerfilTalentoRepository repository) {
        this.repository = repository;
    }

    public PerfilTalento handle(ObtenerPerfilTalentoQuery query) {
        return repository.findById(query.talentoId())
                .orElseThrow(() -> new IllegalArgumentException("Perfil no encontrado"));
    }
}
