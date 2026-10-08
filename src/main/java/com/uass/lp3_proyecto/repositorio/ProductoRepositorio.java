package com.uass.lp3_proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uass.lp3_proyecto.entidad.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
}
