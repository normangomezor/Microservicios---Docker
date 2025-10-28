package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.Cliente;
import org.example.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente create(Cliente cliente) {
        if(cliente.getIdentificacion() != null && clienteRepository.existsByIdentificacion(cliente.getIdentificacion())){
            throw  new IllegalArgumentException("El cliente existe en el sistema");
        }
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id,Cliente cliente) {
        Cliente cl  = clienteRepository.findById(id).orElseThrow(() -> new  IllegalArgumentException("El cliente no existe"));
        cl.setNombre(cliente.getNombre());
        cl.setEdad(cliente.getEdad());
        cl.setDireccion( cliente.getDireccion());
        cl.setTelefono(cliente.getTelefono());
        return clienteRepository.save(cl);
    }

    public List<Cliente> findAll() {return clienteRepository.findAll();}
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }
    public Cliente delete(Long id){
        Cliente cle= clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El cliente no encontrado"));
        cle.setEstado(false);
        return clienteRepository.save(cle);
    }
}
