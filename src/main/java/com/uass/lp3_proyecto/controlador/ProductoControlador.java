package com.uass.lp3_proyecto.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uass.lp3_proyecto.entidad.Producto;
import com.uass.lp3_proyecto.servicio.ProductoServicio;

/**
 * Controlador REST para la entidad Producto.
 * Expone tres endpoints:
 *   POST   /productos        → alta de un producto (201 Created)
 *   GET    /productos        → listado completo     (200 OK)
 *   GET    /productos/{id}   → consulta individual  (200 OK | 404 Not Found)
 *
 * LP3 — Clase 9 (08/10/2026)
 */
@RestController
@RequestMapping("/productos")
public class ProductoControlador {

    private final ProductoServicio servicio;

    public ProductoControlador(ProductoServicio servicio) {
        this.servicio = servicio;
    }

    /**
     * Alta de un producto.
     * Recibe un JSON en el cuerpo de la petición y retorna el objeto
     * guardado con el id asignado por la base de datos.
     *
     * @param producto objeto deserializado por Jackson desde el cuerpo JSON
     * @return 201 Created + el producto guardado
     */
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        Producto guardado = servicio.guardar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    /**
     * Listado de todos los productos.
     *
     * @return 200 OK + arreglo JSON con todos los productos
     */
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(servicio.listarTodos());
    }

    /**
     * Consulta individual por identificador.
     * Si el producto existe retorna 200 OK con el objeto;
     * si no existe retorna 404 Not Found con cuerpo vacío.
     *
     * @param id identificador extraído de la URL con @PathVariable
     * @return 200 OK + producto, o 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Long id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
