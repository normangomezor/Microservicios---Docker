package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.Cuenta;
import org.example.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;

    public Cuenta create(Cuenta cuenta) {
        if (cuentaRepository.existsByNumeroCuneta(cuenta.getNumeroCuneta())) {
            throw new IllegalArgumentException("La cuenta ya existe");
        }
        return cuentaRepository.save(cuenta);
    }

    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }

    public Cuenta update(Long id, Cuenta cuenta) {
        Cuenta cn = cuentaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La cuenta no existe"));
        cn.setTipoCuenta(cuenta.getTipoCuenta());
        cn.setSaldoInicial(cuenta.getSaldoInicial());
        cn.setEstado(cuenta.getEstado());
        return cuentaRepository.save(cn);
    }
}
