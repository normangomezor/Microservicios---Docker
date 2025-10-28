-- ==========================
-- CREACIÓN DE TABLAS
-- ==========================
CREATE TABLE IF NOT EXISTS persona (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100),
    genero VARCHAR(10),
    edad INT,
    identificacion VARCHAR(20) UNIQUE,
    direccion VARCHAR(150),
    telefono VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS cliente (
    id SERIAL PRIMARY KEY,
    persona_id INT REFERENCES persona(id),
    clienteid VARCHAR(50) UNIQUE,
    contrasena VARCHAR(50),
    estado BOOLEAN
);

CREATE TABLE IF NOT EXISTS cuenta (
    id SERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(50) UNIQUE,
    tipo_cuenta VARCHAR(20),
    saldo_inicial DECIMAL(10,2),
    estado BOOLEAN,
    cliente_id INT REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS movimiento (
    id SERIAL PRIMARY KEY,
    fecha TIMESTAMP,
    tipo_movimiento VARCHAR(20),
    valor DECIMAL(10,2),
    saldo DECIMAL(10,2),
    cuenta_id INT REFERENCES cuenta(id)
);

-- ==========================
-- DATOS INICIALES
-- ==========================
INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono)
VALUES
('José Lema', 'M', 32, '1234567890', 'Otavalo s/n y principal', '098254785'),
('Marianela Montalvo', 'F', 28, '1234567891', 'Amazonas y NNUU', '097548965'),
('Juan Osorio', 'M', 40, '1234567892', '13 de junio y equinoccial', '098874587');

INSERT INTO cliente (persona_id, clienteid, contrasena, estado)
VALUES
(1, 'jlema', '1234', TRUE),
(2, 'mmontalvo', '5678', TRUE),
(3, 'josorio', '1245', TRUE);

INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id)
VALUES
('478758', 'Ahorros', 2000.00, TRUE, 1),
('225487', 'Corriente', 100.00, TRUE, 2),
('495878', 'Ahorros', 0.00, TRUE, 3),
('496825', 'Ahorros', 540.00, TRUE, 2),
('585545', 'Corriente', 1000.00, TRUE, 1);

INSERT INTO movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES
('2022-02-10', 'Retiro', -575.00, 1425.00, 1),
('2022-02-10', 'Depósito', 600.00, 700.00, 2),
('2022-02-10', 'Depósito', 150.00, 150.00, 3),
('2022-02-10', 'Retiro', -540.00, 0.00, 4);
