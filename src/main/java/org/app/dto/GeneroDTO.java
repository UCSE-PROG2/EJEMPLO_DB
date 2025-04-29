package org.app.dto;

public class GeneroDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private boolean esActivo;

    public GeneroDTO() {
    }

    public GeneroDTO(Long id, String nombre, String descripcion, boolean esActivo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esActivo = esActivo;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEsActivo() {
        return esActivo;
    }

    public void setEsActivo(boolean esActivo) {
        this.esActivo = esActivo;
    }
} 