package com.somosayni.perfiles.application.command;

import com.somosayni.perfiles.application.port.PerfilTalentoRepository;
import com.somosayni.perfiles.domain.model.PerfilTalento;
import org.springframework.stereotype.Component;

@Component
public class EditarPerfilTalentoCommandHandler {

    private final PerfilTalentoRepository repository;

    public EditarPerfilTalentoCommandHandler(PerfilTalentoRepository repository) {
        this.repository = repository;
    }

    public PerfilTalento handle(EditarPerfilTalentoCommand command) {
        String id = command.talentoId();
        PerfilTalento perfil = repository.findById(id)
                .or(() -> repository.findByUsuarioId(id))
                .orElseThrow(() -> new IllegalArgumentException("Perfil no encontrado"));

        if (command.nombreCompleto() != null) perfil.actualizarNombreCompleto(command.nombreCompleto());
        if (command.ubicacion() != null) perfil.actualizarUbicacion(command.ubicacion());
        if (command.sobreMi() != null) perfil.actualizarSobreMi(command.sobreMi());

        return repository.save(perfil);
    }
}
