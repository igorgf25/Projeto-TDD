INSERT INTO tb_usuario(cartao, senha, nome) VALUES ("Admin", "$2a$10$VGsgveVXTNevOaqi7hXt/eK2xe5P9.NEflfeByjMugrC5UuDKh4H2", "admin");
INSERT INTO tb_usuario(cartao, senha, nome) VALUES ("5115452799278431", "$2a$10$VGsgveVXTNevOaqi7hXt/eK2xe5P9.NEflfeByjMugrC5UuDKh4H2", "usuario");

INSERT INTO tb_role(role) VALUES ("ROLE_ADMIN");
INSERT INTO tb_role(role) VALUES ("ROLE_USER");

INSERT INTO tb_user_role(usuario_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role(usuario_id, role_id) VALUES (2, 2);

INSERT INTO tb_conta(usuario_id, saldo) VALUES (1, 1600.00);
INSERT INTO tb_conta(usuario_id, saldo) VALUES (2, 2500.00);

INSERT INTO tb_caixa_eletronico(notas100, notas50, notas20, notas10, valor_total) VALUES (100, 100, 100, 100, 18000);