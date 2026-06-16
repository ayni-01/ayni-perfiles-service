package com.somosayni.perfiles.application.command;

public record CrearPerfilEmpresaCommand(
        String usuarioId,
        String razonSocial,
        String ruc,
        String sector
) {}
