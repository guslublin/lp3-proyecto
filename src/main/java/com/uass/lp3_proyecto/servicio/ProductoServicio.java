package com.uass.lp3_proyecto.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uass.lp3_proyecto.entidad.Producto;
import com.uass.lp3_proyecto.repositorio.ProductoRepositorio;

/**
 * Capa de servicio para la entidad Producto.
 * Contiene la lógica de negocio y actúa de intermediaria entre el
 * controlador y el repositorio.
 *
 * LP3 — Clase 9 (08/10/2026)
 */
@Service
public class ProductoServicio {

    private final ProductoRepositorio repositorio;

    public ProductoServicio(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Guarda un producto nuevo en la base de datos.
     *
     * @param producto objeto recibido desde el controlador (sin id)
     * @return el producto guardado con el id asignado por PostgreSQL
     */
    public Producto guardar(Producto producto) {
        return repositorio.save(producto);
    }

    /**
     * Retorna todos los productos de la tabla.
     *
     * @return lista (puede estar vacía si la tabla no tiene filas)
     */
    public List<Producto> listarTodos() {
        return repositorio.findAll();
    }

    /**
     * Busca un producto por su identificador.
     *
     * @param id identificador a buscar
     * @return Optional con el producto si existe, o vacío si no existe
     */
    public Optional<Producto> buscarPorId(Long id) {
        return repositorio.findById(id);
    }
}
