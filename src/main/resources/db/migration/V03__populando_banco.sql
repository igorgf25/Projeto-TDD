INSERT INTO tb_usuario(cartao, senha, nome) VALUES ("Admin", "$2a$10$VGsgveVXTNevOaqi7hXt/eK2xe5P9.NEflfeByjMugrC5UuDKh4H2", "admin");
INSERT INTO tb_usuario(cartao, senha, nome) VALUES ("User", "$2a$10$VGsgveVXTNevOaqi7hXt/eK2xe5P9.NEflfeByjMugrC5UuDKh4H2", "usuario");

INSERT INTO tb_role(role) VALUES ("ROLE_ADMIN");
INSERT INTO tb_role(role) VALUES ("ROLE_USER");

INSERT INTO tb_user_role(usuario_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role(usuario_id, role_id) VALUES (2, 2);