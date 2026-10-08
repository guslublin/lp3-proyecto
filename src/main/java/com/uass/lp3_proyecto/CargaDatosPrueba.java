package com.uass.lp3_proyecto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component; // ← desactivado en Clase 9

import com.uass.lp3_proyecto.entidad.Producto;
import com.uass.lp3_proyecto.repositorio.ProductoRepositorio;

/**
 * Carga inicial de datos de prueba.
 *
 * CLASE 9: @Component está comentado para que Spring no ejecute esta clase
 * automáticamente al arrancar. Los datos se cargan desde Postman con POST.
 * Para reactivar la carga automática, descomentar la línea @Component.
 *
 * LP3 — Clase 9 (08/10/2026)
 */
// @Component  ← desactivado en Clase 9; descomentar para reactivar
public class CargaDatosPrueba implements CommandLineRunner {

    private final ProductoRepositorio repositorio;

    public CargaDatosPrueba(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void run(String... args) {
        if (repositorio.count() == 0) {
            repositorio.save(new Producto("P-001", "Cuaderno",
                    "Cuaderno universitario de 100 hojas",
                    new BigDecimal("15000"), 50, true, LocalDate.now()));
            repositorio.save(new Producto("P-002", "Bolígrafo",
                    "Bolígrafo de tinta azul",
                    new BigDecimal("3500"), 200, true, LocalDate.now()));
        }
        System.out.println("Productos en la base: " + repositorio.count());
    }
}
