package org.example.controller;

import org.example.model.Cuenta;
import org.example.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<Cuenta> getCuentas() {
        return cuentaService.findAll();
    }

    @PostMapping
    public ResponseEntity<Cuenta> saveCuenta(@RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaService.create(cuenta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaService.update(id, cuenta));
    }
}
