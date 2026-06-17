package com.somosayni.perfiles.application.command;

import com.somosayni.perfiles.application.port.PerfilEmpresaRepository;
import com.somosayni.perfiles.domain.model.PerfilEmpresa;
import org.springframework.stereotype.Component;

@Component
public class ValidarEmpresaCommandHandler {

    private final PerfilEmpresaRepository repository;

    public ValidarEmpresaCommandHandler(PerfilEmpresaRepository repository) {
        this.repository = repository;
    }

    public PerfilEmpresa handle(ValidarEmpresaCommand command) {
        String id = command.empresaId();
        PerfilEmpresa perfil = repository.findById(id)
                .or(() -> repository.findByUsuarioId(id))
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada"));

        perfil.validar();
        return repository.save(perfil);
    }
}
