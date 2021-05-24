/* Tabela de Clientes */
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Andres', 'Guzman', 'profesor@bolsadeideas.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('John', 'Doe', 'john.doe@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Jane', 'Doe', 'jane.doe@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('James', 'Gosling', 'james.gosling@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Bruce', 'Lee', 'bruce.lee@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Johnny', 'Doe', 'johnny.doe@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('John', 'Roe', 'john.roe@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Jane', 'Roe', 'jane.roe@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Richard', 'Doe', 'richard.doe@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Janie', 'Doe', 'janie.doe@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Phillip', 'Webb', 'phillip.webb@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Stephane', 'Nicoll', 'stephane.nicoll@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Sam', 'Brannen', 'sam.brannen@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Janie', 'Roe', 'janie.roe@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('John', 'Smith', 'john.smith@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Joe', 'Bloggs', 'joe.bloggs@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('John', 'Stiles', 'john.stiles@gmail.com', NOW(), '');
INSERT INTO clientes (nome, sobrenome, email, create_at, foto) VALUES('Richard', 'Roe', 'stiles.roe@gmail.com', NOW(), '');

/* Tabela produtos */
INSERT INTO produtos (nome, preco, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO produtos (nome, preco, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO produtos (nome, preco, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO produtos (nome, preco, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO produtos (nome, preco, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO produtos (nome, preco, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO produtos (nome, preco, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

/* Tabela Fatura */
INSERT INTO fatura (descricao, observacao, cliente_id, create_at) VALUES('Fatura da oficina', null, 1, NOW());
INSERT INTO fatura_itens (resultado, fatura_id, produto_id) VALUES(1, 1, 1);
INSERT INTO fatura_itens (resultado, fatura_id, produto_id) VALUES(2, 1, 4);
INSERT INTO fatura_itens (resultado, fatura_id, produto_id) VALUES(1, 1, 5);
INSERT INTO fatura_itens (resultado, fatura_id, produto_id) VALUES(1, 1, 7);

INSERT INTO fatura (descricao, observacao, cliente_id, create_at) VALUES('Fatura de Bicicleta', 'Observacao importante!', 1, NOW());
INSERT INTO fatura_itens (resultado, fatura_id, produto_id) VALUES(3, 2, 6);

/* Tabela Usuarios e papeis */
INSERT INTO `users` (username, password, enabled) VALUES ('felipe','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',1);
INSERT INTO `users` (username, password, enabled) VALUES ('admin','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',1);

INSERT INTO `authorities` (user_id, authority) VALUES (1,'ROLE_USER');
INSERT INTO `authorities` (user_id, authority) VALUES (2,'ROLE_ADMIN');
INSERT INTO `authorities` (user_id, authority) VALUES (2,'ROLE_USER');