package com.somosayni.perfiles.application.command;

import com.somosayni.perfiles.application.port.PerfilTalentoRepository;
import com.somosayni.perfiles.domain.model.PerfilTalento;
import org.springframework.stereotype.Component;

@Component
public class CrearPerfilTalentoCommandHandler {

    private final PerfilTalentoRepository repository;

    public CrearPerfilTalentoCommandHandler(PerfilTalentoRepository repository) {
        this.repository = repository;
    }

    public PerfilTalento handle(CrearPerfilTalentoCommand command) {
        if (repository.findByUsuarioId(command.usuarioId()).isPresent()) {
            throw new IllegalStateException("El usuario ya tiene un perfil");
        }

        PerfilTalento perfil = new PerfilTalento(command.usuarioId(), command.nombreCompleto());
        return repository.save(perfil);
    }
}
