package com.somosayni.perfiles.application.command;

public record EditarPerfilTalentoCommand(
        String talentoId,
        String nombreCompleto,
        String ubicacion,
        String sobreMi
) {}
