package com.somosayni.perfiles.infrastructure.persistence.entity;

import com.somosayni.perfiles.domain.model.PerfilEmpresa;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "perfil_empresa")
public class PerfilEmpresaEntity {

    @Id
    private String id;

    @Column(name = "usuario_id", nullable = false, unique = true)
    private String usuarioId;

    @Column(name = "razon_social", nullable = false)
    private String razonSocial;

    @Column(name = "ruc", nullable = false, unique = true)
    private String ruc;

    @Column(name = "logo")
    private String logo;

    @Column(name = "descripcion", length = 2000)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "sector")
    private PerfilEmpresa.Sector sector;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_validacion", nullable = false)
    private PerfilEmpresa.EstadoValidacion estadoValidacion;

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

    public PerfilEmpresa toDomain() {
        PerfilEmpresa empresa = new PerfilEmpresa(usuarioId, razonSocial, ruc);
        empresa.setId(id);
        if (logo != null) empresa.actualizarLogo(logo);
        if (descripcion != null) empresa.actualizarDescripcion(descripcion);
        empresa.setSectorInternal(sector);
        empresa.setEstadoValidacionInternal(estadoValidacion);
        return empresa;
    }

    public static PerfilEmpresaEntity fromDomain(PerfilEmpresa perfil) {
        PerfilEmpresaEntity entity = new PerfilEmpresaEntity();
        entity.id = perfil.getId();
        entity.usuarioId = perfil.getUsuarioId();
        entity.razonSocial = perfil.getRazonSocial();
        entity.ruc = perfil.getRuc();
        entity.logo = perfil.getLogo();
        entity.descripcion = perfil.getDescripcion();
        entity.sector = perfil.getSector();
        entity.estadoValidacion = perfil.getEstadoValidacion();
        return entity;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }
    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public PerfilEmpresa.Sector getSector() { return sector; }
    public void setSector(PerfilEmpresa.Sector sector) { this.sector = sector; }
    public PerfilEmpresa.EstadoValidacion getEstadoValidacion() { return estadoValidacion; }
    public void setEstadoValidacion(PerfilEmpresa.EstadoValidacion estadoValidacion) { this.estadoValidacion = estadoValidacion; }
}
