package com.somosayni.perfiles.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record CrearPerfilTalentoRequest(
        @NotBlank(message = "Nombre completo es obligatorio")
        String nombreCompleto
) {}
