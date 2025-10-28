package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.Movimiento;
import org.example.repository.CuentaRepository;
import org.example.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.model.*;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public Movimiento registrarMovimiento(@org.jetbrains.annotations.NotNull Movimiento movimiento){

        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId()).orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        Double saldoActual = cuenta.getSaldoInicial();
        Double valorMovimiento= movimiento.getValor();

        if(movimiento.getTipoMovimiento().equalsIgnoreCase("Retiro")){
            if(saldoActual.compareTo(valorMovimiento)<0){
                throw new IllegalArgumentException("Saldo no disponible");
            }
            saldoActual -= valorMovimiento;
        } else if (movimiento.getTipoMovimiento().equalsIgnoreCase("Deposito")) {
            saldoActual += valorMovimiento;
        } else {
            throw new IllegalArgumentException("Tipo de movimiento no valido");
        }

        cuenta.setSaldoInicial(saldoActual);
        cuentaRepository.save(cuenta);

        movimiento.setCuenta(cuenta);
        movimiento.setFecha(new Date());
        movimiento.setSaldo(saldoActual);

        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> findAll(){
        return movimientoRepository.findAll();
    }
}
