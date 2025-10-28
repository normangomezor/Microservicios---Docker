package org.example.controller;

import org.example.model.Cuenta;
import org.example.model.Movimiento;
import org.example.repository.CuentaRepository;
import org.example.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @GetMapping("/generar")
    public Map<String, Object> generarReporte(
            @RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFinal) {

        Map<String, Object> reporte = new LinkedHashMap<>();
        reporte.put("clienteId", clienteId);

        List<Map<String, Object>> cuentasList = new ArrayList<>();
        List<Cuenta> cuentas = cuentaRepository.findByCliente(clienteId);

        for (Cuenta cu : cuentas) {
            Map<String, Object> cuentaMap = new LinkedHashMap<>();
            cuentaMap.put("numeroCuenta", cu.getNumeroCuneta());
            cuentaMap.put("tipoCuenta", cu.getTipoCuenta());
            cuentaMap.put("saldo", cu.getSaldoInicial());

            List<Movimiento> movimientos = movimientoRepository.findByCuentaAndFechaBetween(cu, fechaInicio, fechaFinal);
            List<Map<String, Object>> movList = new ArrayList<>();

            for (Movimiento m : movimientos) {
                Map<String, Object> movMap = new LinkedHashMap<>();
                movMap.put("fecha", m.getFecha());
                movMap.put("tipoMovimiento", m.getTipoMovimiento());
                movMap.put("valor", m.getValor());
                movList.add(movMap);
            }

            cuentaMap.put("movimientos", movList);
            cuentasList.add(cuentaMap);
        }

        reporte.put("cuentas", cuentasList);
        return reporte;
    }
}
