package org.app.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class LibroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private Long generoId;
    private String generoNombre;
    private boolean esActivo;

    public LibroDTO() {
    }

    public LibroDTO(Long id, String titulo, String autor,
                   Long generoId, String generoNombre, boolean esActivo) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.generoId = generoId;
        this.generoNombre = generoNombre;
        this.esActivo = esActivo;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Long getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Long generoId) {
        this.generoId = generoId;
    }

    public String getGeneroNombre() {
        return generoNombre;
    }

    public void setGeneroNombre(String generoNombre) {
        this.generoNombre = generoNombre;
    }

    public boolean isEsActivo() {
        return esActivo;
    }

    public void setEsActivo(boolean esActivo) {
        this.esActivo = esActivo;
    }
} 