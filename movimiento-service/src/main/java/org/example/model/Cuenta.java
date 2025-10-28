package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


@Entity
@Table(name = "cuentas",uniqueConstraints = @UniqueConstraint(columnNames ="numero_cuenta"))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_cuenta",  nullable = false,unique = true)
    private String numeroCuneta;

    @Column(name = "tipo_cuenta", nullable = false)
    private String tipoCuenta;

    @Column(name = "saldo_inicial", nullable = false)
    private Double saldoInicial;

    private Boolean estado = true;

    @Column(name = "cliente_id")
    private Long cliente;

    public Cuenta() {
    }

    public Cuenta(Long id, String numeroCuneta, String tipoCuenta, Double saldoInicial, Boolean estado, Long cliente) {
        this.id = id;
        this.numeroCuneta = numeroCuneta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCuneta() {
        return numeroCuneta;
    }

    public void setNumeroCuneta(String numeroCuneta) {
        this.numeroCuneta = numeroCuneta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }
}
