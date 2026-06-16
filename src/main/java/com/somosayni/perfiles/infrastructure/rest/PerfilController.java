package com.somosayni.perfiles.infrastructure.rest;

import com.somosayni.perfiles.application.command.*;
import com.somosayni.perfiles.application.query.*;
import com.somosayni.perfiles.domain.model.PerfilTalento;
import com.somosayni.perfiles.domain.model.PerfilEmpresa;
import com.somosayni.perfiles.infrastructure.rest.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/perfiles")
public class PerfilController {

    private final CrearPerfilTalentoCommandHandler crearTalentoHandler;
    private final EditarPerfilTalentoCommandHandler editarTalentoHandler;
    private final GenerarCVCommandHandler generarCVHandler;
    private final CrearPerfilEmpresaCommandHandler crearEmpresaHandler;
    private final ValidarEmpresaCommandHandler validarEmpresaHandler;
    private final ObtenerPerfilTalentoQueryHandler obtenerTalentoHandler;
    private final ObtenerPerfilEmpresaQueryHandler obtenerEmpresaHandler;

    public PerfilController(
            CrearPerfilTalentoCommandHandler crearTalentoHandler,
            EditarPerfilTalentoCommandHandler editarTalentoHandler,
            GenerarCVCommandHandler generarCVHandler,
            CrearPerfilEmpresaCommandHandler crearEmpresaHandler,
            ValidarEmpresaCommandHandler validarEmpresaHandler,
            ObtenerPerfilTalentoQueryHandler obtenerTalentoHandler,
            ObtenerPerfilEmpresaQueryHandler obtenerEmpresaHandler) {
        this.crearTalentoHandler = crearTalentoHandler;
        this.editarTalentoHandler = editarTalentoHandler;
        this.generarCVHandler = generarCVHandler;
        this.crearEmpresaHandler = crearEmpresaHandler;
        this.validarEmpresaHandler = validarEmpresaHandler;
        this.obtenerTalentoHandler = obtenerTalentoHandler;
        this.obtenerEmpresaHandler = obtenerEmpresaHandler;
    }

    @PostMapping("/talento")
    public ResponseEntity<PerfilTalento> crearPerfilTalento(
            @RequestHeader("X-User-Id") String usuarioId,
            @Valid @RequestBody CrearPerfilTalentoRequest request) {
        CrearPerfilTalentoCommand command = new CrearPerfilTalentoCommand(usuarioId, request.nombreCompleto());
        PerfilTalento perfil = crearTalentoHandler.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(perfil);
    }

    @PutMapping("/talento/{id}")
    public ResponseEntity<PerfilTalento> editarPerfilTalento(
            @PathVariable String id,
            @Valid @RequestBody EditarPerfilTalentoRequest request) {
        EditarPerfilTalentoCommand command = new EditarPerfilTalentoCommand(id, request.nombreCompleto(), request.ubicacion(), request.sobreMi());
        PerfilTalento perfil = editarTalentoHandler.handle(command);
        return ResponseEntity.ok(perfil);
    }

    @PostMapping("/talento/{id}/cv")
    public ResponseEntity<PerfilTalento> generarCV(
            @PathVariable String id,
            @RequestBody String contenidoCv) {
        GenerarCVCommand command = new GenerarCVCommand(id, contenidoCv);
        PerfilTalento perfil = generarCVHandler.handle(command);
        return ResponseEntity.ok(perfil);
    }

    @GetMapping("/talento/{id}")
    public ResponseEntity<PerfilTalento> obtenerPerfilTalento(@PathVariable String id) {
        PerfilTalento perfil = obtenerTalentoHandler.handle(new ObtenerPerfilTalentoQuery(id));
        return ResponseEntity.ok(perfil);
    }

    @PostMapping("/empresa")
    public ResponseEntity<PerfilEmpresa> crearPerfilEmpresa(
            @RequestHeader("X-User-Id") String usuarioId,
            @Valid @RequestBody CrearPerfilEmpresaRequest request) {
        CrearPerfilEmpresaCommand command = new CrearPerfilEmpresaCommand(usuarioId, request.razonSocial(), request.ruc(), request.sector());
        PerfilEmpresa perfil = crearEmpresaHandler.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(perfil);
    }

    @PutMapping("/empresa/{id}/validar")
    public ResponseEntity<PerfilEmpresa> validarEmpresa(@PathVariable String id) {
        PerfilEmpresa perfil = validarEmpresaHandler.handle(new ValidarEmpresaCommand(id));
        return ResponseEntity.ok(perfil);
    }

    @GetMapping("/empresa/{id}")
    public ResponseEntity<PerfilEmpresa> obtenerPerfilEmpresa(@PathVariable String id) {
        PerfilEmpresa perfil = obtenerEmpresaHandler.handle(new ObtenerPerfilEmpresaQuery(id));
        return ResponseEntity.ok(perfil);
    }
}
