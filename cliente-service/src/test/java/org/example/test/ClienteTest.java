package org.example.test;

import org.example.ClienteServiceApplication;
import org.example.model.Cliente;
import org.example.repository.ClienteRepository;
import org.example.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ClienteServiceApplication.class)
@ActiveProfiles("test")
public class ClienteTest {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
    }

    @Test
    void testCrearCliente() {
        Cliente cliente = new Cliente(true, "CLI001", "1234");
        cliente.setNombre("José Lema");
        cliente.setGenero("Masculino");
        cliente.setEdad(35);
        cliente.setIdentificacion("1234567890");
        cliente.setDireccion("Otavalo s/n y principal");
        cliente.setTelefono("0987654321");

        Cliente result = clienteService.create(cliente);

        assertNotNull(result.getId(), "El cliente debe tener un ID generado");
        assertEquals("José Lema", result.getNombre());
        assertTrue(result.getEstado());
        assertEquals("1234567890", result.getIdentificacion());
    }

    @Test
    void testBuscarClientePorId() {
        Cliente cliente = new Cliente(true, "CLI002", "5678");
        cliente.setNombre("Marianela");
        cliente.setGenero("Femenino");
        cliente.setEdad(29);
        cliente.setIdentificacion("9876543210");
        cliente.setDireccion("Quito, Av. Amazonas");
        cliente.setTelefono("0999988777");

        cliente = clienteRepository.save(cliente);

        Optional<Cliente> found = clienteService.findById(cliente.getId());

        assertTrue(found.isPresent(), "El cliente debe existir en la base de datos");
        assertEquals("Marianela", found.get().getNombre());
        assertEquals("9876543210", found.get().getIdentificacion());
    }

}
