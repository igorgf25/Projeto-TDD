CREATE TABLE tb_usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cartao VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE tb_role (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  role VARCHAR(255) NOT NULL
);

CREATE TABLE tb_user_role (
    usuario_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES tb_usuario(id),
    FOREIGN KEY (role_id) REFERENCES tb_role(id)
)