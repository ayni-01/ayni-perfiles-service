package com.somosayni.perfiles.domain.model;

import com.somosayni.shared.domain.model.AggregateRoot;

public class PerfilEmpresa extends AggregateRoot {

    private String usuarioId;
    private String razonSocial;
    private String ruc;
    private String logo;
    private String descripcion;
    private Sector sector;
    private EstadoValidacion estadoValidacion;

    public PerfilEmpresa() {}

    public PerfilEmpresa(String usuarioId, String razonSocial, String ruc) {
        this.usuarioId = usuarioId;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.estadoValidacion = EstadoValidacion.PENDIENTE;
    }

    public void validar() {
        this.estadoValidacion = EstadoValidacion.VALIDADO;
    }

    public void rechazar() {
        this.estadoValidacion = EstadoValidacion.RECHAZADO;
    }

    public void actualizarDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void actualizarLogo(String logo) {
        this.logo = logo;
    }

    public String getUsuarioId() { return usuarioId; }
    public String getRazonSocial() { return razonSocial; }
    public String getRuc() { return ruc; }
    public String getLogo() { return logo; }
    public String getDescripcion() { return descripcion; }
    public Sector getSector() { return sector; }
    public EstadoValidacion getEstadoValidacion() { return estadoValidacion; }

    /** Usado solo por la capa de persistencia para reconstituir el aggregate */
    public void setSectorInternal(Sector sector) { this.sector = sector; }
    /** Usado solo por la capa de persistencia para reconstituir el aggregate */
    public void setEstadoValidacionInternal(EstadoValidacion estadoValidacion) { this.estadoValidacion = estadoValidacion; }

    public enum Sector {
        TECNOLOGIA, FINANZAS, SALUD, EDUCACION, COMERCIO, INDUSTRIA, SERVICIOS
    }

    public enum EstadoValidacion {
        PENDIENTE, VALIDADO, RECHAZADO
    }
}
