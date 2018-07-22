CREATE SCHEMA `revisao-textual`

CREATE TABLE servico (
	id BIGINT NOT NULL AUTO_INCREMENT,
 	descricao VARCHAR(255) NOT NULL,
 	caracteristicas VARCHAR(255) NOT NULL,
 	primary key (id)
);

CREATE TABLE servico_valor (
	id BIGINT NOT NULL AUTO_INCREMENT,
	forma_pagamento VARCHAR(1) NOT NULL,
	valor DECIMAL NOT NULL,
	id_servico BIGINT NOT NULL,
	PRIMARY KEY(id),
    foreign key(id_servico) references servico(id)
);


INSERT INTO `servico` (`descricao`, `caracteristicas`) VALUES ('Trabalho Academico', 'Trabalho em formato academico.');
INSERT INTO `servico_valor` (`forma_pagamento`, `valor`, `id_servico`) VALUES ('0', 20.00, 1);
INSERT INTO `servico_valor` (`forma_pagamento`, `valor`, `id_servico`) VALUES ('1', 1.00, 1);
INSERT INTO `servico_valor` (`forma_pagamento`, `valor`, `id_servico`) VALUES ('2', 5.00, 1);

INSERT INTO `servico` (`descricao`, `caracteristicas`) VALUES ('Trabalho Cientifico', 'Trabalho em formato cientifico.');
INSERT INTO `servico_valor` (`forma_pagamento`, `valor`, `id_servico`) VALUES ('0', 21.00, 2);
INSERT INTO `servico_valor` (`forma_pagamento`, `valor`, `id_servico`) VALUES ('1', 2.00, 2);
INSERT INTO `servico_valor` (`forma_pagamento`, `valor`, `id_servico`) VALUES ('2', 6.00, 2);

INSERT INTO `servico` (`descricao`, `caracteristicas`) VALUES ('TCC', 'Trabalho de conclusao de curso.');
INSERT INTO `servico_valor` (`forma_pagamento`, `valor`, `id_servico`) VALUES ('0', 30.00, 3);
INSERT INTO `servico_valor` (`forma_pagamento`, `valor`, `id_servico`) VALUES ('1', 3.00, 3);
INSERT INTO `servico_valor` (`forma_pagamento`, `valor`, `id_servico`) VALUES ('2', 7.00, 3);

INSERT INTO `servico` (`descricao`, `caracteristicas`) VALUES ('Artigo', 'Artigos para sites, revistas e etc.');
INSERT INTO `servico_valor` (`forma_pagamento`, `valor`, `id_servico`) VALUES ('0', 10.00, 4);
INSERT INTO `servico_valor` (`forma_pagamento`, `valor`, `id_servico`) VALUES ('1', 1.00, 4);
INSERT INTO `servico_valor` (`forma_pagamento`, `valor`, `id_servico`) VALUES ('2', 3.00, 4);