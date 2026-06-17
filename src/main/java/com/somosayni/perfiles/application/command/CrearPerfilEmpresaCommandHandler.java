package com.somosayni.perfiles.application.command;

import com.somosayni.perfiles.application.port.PerfilEmpresaRepository;
import com.somosayni.perfiles.domain.model.PerfilEmpresa;
import org.springframework.stereotype.Component;

@Component
public class CrearPerfilEmpresaCommandHandler {

    private final PerfilEmpresaRepository repository;

    public CrearPerfilEmpresaCommandHandler(PerfilEmpresaRepository repository) {
        this.repository = repository;
    }

    public PerfilEmpresa handle(CrearPerfilEmpresaCommand command) {
        if (repository.findByUsuarioId(command.usuarioId()).isPresent()) {
            throw new IllegalStateException("El usuario ya tiene un perfil de empresa");
        }

        if (repository.findByRuc(command.ruc()).isPresent()) {
            throw new IllegalArgumentException("El RUC ya está registrado");
        }

        PerfilEmpresa perfil = new PerfilEmpresa(command.usuarioId(), command.razonSocial(), command.ruc());
        if (command.sector() != null && !command.sector().isBlank()) {
            try {
                perfil.setSectorInternal(PerfilEmpresa.Sector.valueOf(command.sector().toUpperCase()));
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Sector inválido: " + command.sector());
            }
        }
        return repository.save(perfil);
    }
}
