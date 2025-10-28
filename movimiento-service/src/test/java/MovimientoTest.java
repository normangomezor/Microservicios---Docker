import org.example.MovimientoServiceApplication;
import org.example.model.Cuenta;
import org.example.model.Movimiento;
import org.example.repository.CuentaRepository;
import org.example.repository.MovimientoRepository;
import org.example.service.MovimientoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MovimientoServiceApplication.class)
@ActiveProfiles("test")
public class MovimientoTest {

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private CuentaRepository cuentaRepository;

    private Cuenta cuenta;

   // @BeforeEach
    public void setUp() {
        cuenta = new Cuenta();
        cuenta.setNumeroCuneta("001");
        cuenta.setTipoCuenta("Ahorro");
        cuenta.setSaldoInicial(1000.0);
        cuenta.setEstado(true);
        cuentaRepository.save(cuenta);
    }

   // @Test
    public void testRegistrarDeposito() {
        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setTipoMovimiento("Deposito");
        movimiento.setValor(200.0);
        movimiento.setFecha(new Date());

        Movimiento resultado = movimientoService.registrarMovimiento(movimiento);

        assertNotNull(resultado.getId());
        assertEquals(1200.0, resultado.getSaldo());
    }

   // @Test
    public void testRegistrarRetiro() {
        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setTipoMovimiento("Retiro");
        movimiento.setValor(300.0);
        movimiento.setFecha(new Date());

        Movimiento resultado = movimientoService.registrarMovimiento(movimiento);

        assertNotNull(resultado.getId());
        assertEquals(700.0, resultado.getSaldo());
    }
}
