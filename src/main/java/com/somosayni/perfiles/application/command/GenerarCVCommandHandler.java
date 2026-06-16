package com.somosayni.perfiles.application.command;

import com.somosayni.perfiles.application.port.PerfilTalentoRepository;
import com.somosayni.perfiles.domain.model.PerfilTalento;
import org.springframework.stereotype.Component;

@Component
public class GenerarCVCommandHandler {

    private final PerfilTalentoRepository repository;

    public GenerarCVCommandHandler(PerfilTalentoRepository repository) {
        this.repository = repository;
    }

    public PerfilTalento handle(GenerarCVCommand command) {
        PerfilTalento perfil = repository.findById(command.talentoId())
                .orElseThrow(() -> new IllegalArgumentException("Perfil no encontrado"));

        perfil.generarCV(command.contenidoCv());
        return repository.save(perfil);
    }
}
