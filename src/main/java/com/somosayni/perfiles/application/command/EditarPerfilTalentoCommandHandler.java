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
        PerfilTalento perfil = repository.findById(command.talentoId())
                .orElseThrow(() -> new IllegalArgumentException("Perfil no encontrado"));

        if (command.nombreCompleto() != null) perfil.actualizarNombreCompleto(command.nombreCompleto());
        if (command.ubicacion() != null) perfil.actualizarUbicacion(command.ubicacion());
        if (command.sobreMi() != null) perfil.actualizarSobreMi(command.sobreMi());

        return repository.save(perfil);
    }
}
