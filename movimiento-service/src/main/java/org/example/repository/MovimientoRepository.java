package org.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.example.model.*;

import java.util.Date;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento,Long> {
    List<Movimiento> findByCuentaAndFechaBetween(Cuenta cuenta, Date fecha1, Date fecha2);
}
