package org.example.repository;

import org.example.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Override
    Optional<Cliente> findById(Long id);
    Boolean existsByIdentificacion(String identificacion);
}
