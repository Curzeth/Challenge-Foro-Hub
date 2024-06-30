CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha DATETIME NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE', 'RESOLVED') NOT NULL,
    autor_id BIGINT,
    curso VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);
