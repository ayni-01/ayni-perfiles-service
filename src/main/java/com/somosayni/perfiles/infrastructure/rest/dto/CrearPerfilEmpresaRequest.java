package com.somosayni.perfiles.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record CrearPerfilEmpresaRequest(
        @NotBlank(message = "Razón social es obligatoria")
        String razonSocial,

        @NotBlank(message = "RUC es obligatorio")
        String ruc,

        String sector
) {}
