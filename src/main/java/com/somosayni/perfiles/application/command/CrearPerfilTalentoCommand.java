package com.somosayni.perfiles.application.command;

public record CrearPerfilTalentoCommand(
        String usuarioId,
        String nombreCompleto
) {}
