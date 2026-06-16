package com.somosayni.perfiles.infrastructure.rest.dto;

public record EditarPerfilTalentoRequest(
        String nombreCompleto,
        String ubicacion,
        String sobreMi
) {}
