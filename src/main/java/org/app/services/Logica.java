package org.app.services;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.app.dto.LibroDTO;
import org.app.models.Genero;
import org.app.models.Libro;
import org.app.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.*;
import java.util.ArrayList;
import java.util.List;

public class Logica {
    private static Logica instance;

    private Logica() {
    }

    public static Logica getInstance() {
        if (instance == null) {
            instance = new Logica();
        }
        return instance;
    }

    // Create
    public LibroDTO crearLibro(LibroDTO libroDTO) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            
            Genero genero = session.get(Genero.class, libroDTO.getGeneroId());
            if (genero == null) {
                throw new RuntimeException("Género no encontrado");
            }

            Libro libro = new Libro();
            libro.setTitulo(libroDTO.getTitulo());
            libro.setAutor(libroDTO.getAutor());
            libro.setGenero(genero);
            libro.setEsActivo(true);

            session.persist(libro);
            session.getTransaction().commit();

            libroDTO.setId(libro.getId());
            libroDTO.setGeneroNombre(genero.getNombre());
            return libroDTO;
        }
    }

    // Read
    public LibroDTO obtenerLibro(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            Libro libro = session.get(Libro.class, id);
            if (libro == null || !libro.isEsActivo()) {
                return null;
            }

            return new LibroDTO(
                libro.getId(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getGenero().getId(),
                libro.getGenero().getNombre(),
                libro.isEsActivo()
            );
        }
    }

    // Read All
    public List<LibroDTO> obtenerTodosLosLibros() {
        try (Session session = HibernateUtil.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Libro> cq = cb.createQuery(Libro.class);
            Root<Libro> root = cq.from(Libro.class);

            // Predicado para la condición WHERE l.esActivo = true
            Predicate activoPredicate = cb.equal(root.get("esActivo"), true);
            cq.where(activoPredicate);

            // Ejecutar la consulta
            List<Libro> libros = session.createQuery(cq).list();

            // Mapear los resultados a LibroDTO
            List<LibroDTO> librosDTO = new ArrayList<>();
            for (Libro libro : libros) {
                librosDTO.add(new LibroDTO(
                        libro.getId(),
                        libro.getTitulo(),
                        libro.getAutor(),
                        libro.getGenero().getId(),
                        libro.getGenero().getNombre(),
                        libro.isEsActivo()
                ));
            }
            return librosDTO;
        }
    }

    // Update
    public LibroDTO actualizarLibro(LibroDTO libroDTO) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            
            Libro libro = session.get(Libro.class, libroDTO.getId());
            if (libro == null || !libro.isEsActivo()) {
                throw new RuntimeException("Libro no encontrado");
            }

            Genero genero = session.get(Genero.class, libroDTO.getGeneroId());
            if (genero == null) {
                throw new RuntimeException("Género no encontrado");
            }

            libro.setTitulo(libroDTO.getTitulo());
            libro.setAutor(libroDTO.getAutor());
            libro.setGenero(genero);

            session.merge(libro);
            session.getTransaction().commit();

            libroDTO.setGeneroNombre(genero.getNombre());
            return libroDTO;
        }
    }

    // Delete (soft delete)
    public void eliminarLibro(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            
            Libro libro = session.get(Libro.class, id);
            if (libro != null) {
                libro.setEsActivo(false);
                session.merge(libro);
            }
            
            session.getTransaction().commit();
        }
    }
} 