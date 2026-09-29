package com.uass.lp3_proyecto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.uass.lp3_proyecto.entidad.Producto;
import com.uass.lp3_proyecto.repositorio.ProductoRepositorio;

@Component
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
