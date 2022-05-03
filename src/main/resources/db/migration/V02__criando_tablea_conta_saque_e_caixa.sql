CREATE TABLE tb_conta(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    saldo DECIMAL NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES tb_usuario(id)
);

CREATE TABLE tb_saque(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    valor DECIMAL NOT NULL,
    horario TIME,
    FOREIGN KEY (usuario_id) REFERENCES tb_usuario(id)
);

CREATE TABLE tb_caixa_eletronico(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    notas100 BIGINT NOT NULL,
    notas50 BIGINT NOT NULL,
    notas20 BIGINT NOT NULL,
    notas10 BIGINT NOT NULL,
    valor_total DECIMAL NOT NULL
);