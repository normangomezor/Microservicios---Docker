package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.model.*;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta,Long> {
    Boolean existsByNumeroCuneta(String numeroCuenta);
    List<Cuenta> findByCliente(Long clienteId);
}

