package com.somosayni.perfiles.domain.model;

import com.somosayni.shared.domain.model.AggregateRoot;
import java.util.List;

public class PerfilTalento extends AggregateRoot {

    private String usuarioId;
    private String nombreCompleto;
    private String fotoPerfil;
    private String ubicacion;
    private String sobreMi;
    private List<Enlace> enlaces;
    private List<Idioma> idiomas;
    private Disponibilidad disponibilidad;
    private CV cv;

    public PerfilTalento() {}

    public PerfilTalento(String usuarioId, String nombreCompleto) {
        this.usuarioId = usuarioId;
        this.nombreCompleto = nombreCompleto;
        this.disponibilidad = Disponibilidad.A_CONSULTAR;
    }

    public void actualizarNombreCompleto(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.isBlank()) {
            throw new IllegalArgumentException("Nombre completo no puede estar vacío");
        }
        this.nombreCompleto = nombreCompleto;
    }

    public void actualizarUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void actualizarSobreMi(String sobreMi) {
        this.sobreMi = sobreMi;
    }

    public void agregarEnlace(Enlace enlace) {
        this.enlaces.add(enlace);
    }

    public void actualizarDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void generarCV(String contenidoCv) {
        this.cv = new CV(contenidoCv);
    }

    public String getUsuarioId() { return usuarioId; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getFotoPerfil() { return fotoPerfil; }
    public String getUbicacion() { return ubicacion; }
    public String getSobreMi() { return sobreMi; }
    public List<Enlace> getEnlaces() { return enlaces; }
    public List<Idioma> getIdiomas() { return idiomas; }
    public Disponibilidad getDisponibilidad() { return disponibilidad; }
    public CV getCv() { return cv; }

    /** Usado solo por la capa de persistencia para reconstituir el aggregate */
    public void setFotoPerfilInternal(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }
    /** Usado solo por la capa de persistencia para reconstituir el aggregate */
    public void setDisponibilidadInternal(Disponibilidad disponibilidad) { this.disponibilidad = disponibilidad; }

    public enum Disponibilidad {
        INMEDIATA, A_CONSULTAR
    }

    public record Enlace(String tipo, String url) {}
    public record Idioma(String idioma, Nivel nivel) { public enum Nivel { BASICO, INTERMEDIO, AVANZADO, NATIVO; } }
    public record CV(String contenido) {}
}
