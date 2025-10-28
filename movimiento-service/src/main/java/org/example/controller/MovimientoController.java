package org.example.controller;

import org.example.model.Cuenta;
import org.example.model.Movimiento;
import org.example.repository.CuentaRepository;
import org.example.repository.MovimientoRepository;
import org.example.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @PostMapping
    public Movimiento registrarMovimiento(@RequestBody Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        Double saldoActual = cuenta.getSaldoInicial();
        Double valorMovimiento = movimiento.getValor();

        if (movimiento.getTipoMovimiento().equalsIgnoreCase("Retiro")) {
            if (saldoActual < valorMovimiento) {
                throw new IllegalArgumentException("Saldo no disponible");
            }
            saldoActual -= valorMovimiento;
        } else if (movimiento.getTipoMovimiento().equalsIgnoreCase("Deposito")) {
            saldoActual += valorMovimiento;
        } else {
            throw new IllegalArgumentException("Tipo de movimiento no válido");
        }

        cuenta.setSaldoInicial(saldoActual);
        cuentaRepository.save(cuenta);

        movimiento.setCuenta(cuenta);
        movimiento.setFecha(new Date());
        movimiento.setSaldo(saldoActual);

        return movimientoRepository.save(movimiento);
    }

    @GetMapping
    public List<Movimiento> findAll() {
        return movimientoRepository.findAll();
    }
}
