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
        PerfilEmpresa perfil = repository.findById(command.empresaId())
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada"));

        perfil.validar();
        return repository.save(perfil);
    }
}
