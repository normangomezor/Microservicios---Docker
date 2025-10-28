package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "clientes",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"cliente_id"}),
        })
@PrimaryKeyJoinColumn(name = "id")
public class Cliente extends Persona {

    @Column(name = "cliente_id", unique = true, nullable = false)
    private String clienteId;

    private String password;
    private Boolean estado = true;

    public Cliente() {}

    public Cliente(Boolean estado, String clienteId, String password) {
        this.estado = estado;
        this.clienteId = clienteId;
        this.password = password;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
