package com.somosayni.perfiles.infrastructure.persistence.entity;

import com.somosayni.perfiles.domain.model.PerfilTalento;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "perfil_talento")
public class PerfilTalentoEntity {

    @Id
    private String id;

    @Column(name = "usuario_id", nullable = false, unique = true)
    private String usuarioId;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "sobre_mi", length = 1000)
    private String sobreMi;

    @ElementCollection
    @CollectionTable(name = "talento_enlaces", joinColumns = @JoinColumn(name = "perfil_talento_id"))
    private List<EnlaceEmbed> enlaces = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "talento_idiomas", joinColumns = @JoinColumn(name = "perfil_talento_id"))
    private List<IdiomaEmbed> idiomas = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "disponibilidad")
    private PerfilTalento.Disponibilidad disponibilidad;

    @Column(name = "cv", columnDefinition = "TEXT")
    private String cv;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        if (id == null) id = UUID.randomUUID().toString();
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public PerfilTalento toDomain() {
        PerfilTalento perfil = new PerfilTalento(usuarioId, nombreCompleto);
        perfil.setId(id);
        perfil.setFotoPerfilInternal(fotoPerfil);
        perfil.actualizarUbicacion(ubicacion);
        perfil.actualizarSobreMi(sobreMi);
        perfil.setDisponibilidadInternal(disponibilidad);
        if (cv != null) perfil.generarCV(cv);
        return perfil;
    }

    public static PerfilTalentoEntity fromDomain(PerfilTalento perfil) {
        PerfilTalentoEntity entity = new PerfilTalentoEntity();
        entity.id = perfil.getId();
        entity.usuarioId = perfil.getUsuarioId();
        entity.nombreCompleto = perfil.getNombreCompleto();
        entity.fotoPerfil = perfil.getFotoPerfil();
        entity.ubicacion = perfil.getUbicacion();
        entity.sobreMi = perfil.getSobreMi();
        entity.disponibilidad = perfil.getDisponibilidad();
        entity.cv = perfil.getCv() != null ? perfil.getCv().contenido() : null;
        return entity;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getFotoPerfil() { return fotoPerfil; }
    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public String getSobreMi() { return sobreMi; }
    public void setSobreMi(String sobreMi) { this.sobreMi = sobreMi; }
    public List<EnlaceEmbed> getEnlaces() { return enlaces; }
    public void setEnlaces(List<EnlaceEmbed> enlaces) { this.enlaces = enlaces; }
    public List<IdiomaEmbed> getIdiomas() { return idiomas; }
    public void setIdiomas(List<IdiomaEmbed> idiomas) { this.idiomas = idiomas; }
    public PerfilTalento.Disponibilidad getDisponibilidad() { return disponibilidad; }
    public void setDisponibilidad(PerfilTalento.Disponibilidad disponibilidad) { this.disponibilidad = disponibilidad; }
    public String getCv() { return cv; }
    public void setCv(String cv) { this.cv = cv; }

    @Embeddable
    public static class EnlaceEmbed {
        private String tipo;
        private String url;
    }

    @Embeddable
    public static class IdiomaEmbed {
        private String idioma;
        private String nivel;
    }
}
