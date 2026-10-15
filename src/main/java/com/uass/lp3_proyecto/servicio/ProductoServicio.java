package com.uass.lp3_proyecto.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uass.lp3_proyecto.entidad.Producto;
import com.uass.lp3_proyecto.repositorio.ProductoRepositorio;

/**
 * Capa de servicio de Producto.
 *
 * Clase 9 (08/10/2026): guardar, listarTodos y buscarPorId.
 * Clase 10 (15/10/2026): actualizar y eliminar.
 */
@Service
public class ProductoServicio {

    private final ProductoRepositorio repositorio;

    public ProductoServicio(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    // ── Clase 9 ──────────────────────────────────────────────────────────

    public Producto guardar(Producto producto) {
        return repositorio.save(producto);
    }

    public List<Producto> listarTodos() {
        return repositorio.findAll();
    }

    public Optional<Producto> buscarPorId(Long id) {
        return repositorio.findById(id);
    }

    // ── Clase 10 ─────────────────────────────────────────────────────────

    /**
     * Reemplaza los datos del producto indicado por los datos recibidos.
     * Devuelve el producto actualizado, o un Optional vacío si el id no existe.
     */
    public Optional<Producto> actualizar(Long id, Producto datos) {
        return repositorio.findById(id)
                .map(existente -> {
                    existente.setCodigo(datos.getCodigo());
                    existente.setNombre(datos.getNombre());
                    existente.setDescripcion(datos.getDescripcion());
                    existente.setPrecio(datos.getPrecio());
                    existente.setStock(datos.getStock());
                    existente.setActivo(datos.getActivo());
                    existente.setFechaAlta(datos.getFechaAlta());
                    return repositorio.save(existente);
                });
    }

    /**
     * Elimina el producto indicado.
     * Devuelve true si existía y se eliminó, false si el id no existe.
     * Se consulta existsById antes porque deleteById ignora en silencio
     * los identificadores inexistentes y no permitiría responder 404.
     */
    public boolean eliminar(Long id) {
        if (!repositorio.existsById(id)) {
            return false;
        }
        repositorio.deleteById(id);
        return true;
    }
}
