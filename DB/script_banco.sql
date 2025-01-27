create database desafio;

use desafio;

CREATE TABLE tb_responsavel_projeto (
	ID INT PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(10) NOT NULL
);

INSERT INTO tb_responsavel_projeto VALUES (null,'ADMFIN'), (null,'ADMPLN'), (null,'ADMAPO');
select * from tb_responsavel_projeto rp join tb_projetos p on (rp.id = p.id_responsavel);

CREATE TABLE tb_projetos (
	ID INT  PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(50) NOT NULL,
    DESCRICAO VARCHAR(100) NOT NULL,
    DATA_INICIO DATE NOT NULL,
    DATA_TERMINO DATE NOT NULL,
    STATUS ENUM('PLANEJADO', 'EM EXECUÇÃO', 'ABORTADO', 'FINALIZADO') NOT NULL,
	ID_RESPONSAVEL INT NOT NULL,
    FOREIGN KEY (ID_RESPONSAVEL) REFERENCES tb_responsavel_projeto(ID)
);

INSERT INTO tb_projetos VALUES (null, 'teste','teste descricao',  '2025-01-20', '2025-01-30', 'asdasda',1);

SELECT * FROM TB_PROJETOS;

CREATE TABLE tb_responsavel_tarefa(
	ID INT PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(5) NOT NULL
);
INSERT INTO tb_responsavel_tarefa VALUES (null ,'PLO'), (null, 'GFU'), (null, 'CTB'), (null, 'GBP');
select * from tb_responsavel_tarefa;

CREATE TABLE tb_tarefas(
	ID INT PRIMARY KEY AUTO_INCREMENT,
	TITULO VARCHAR(25) NOT NULL,
    DESCRICAO VARCHAR(100),
    PRAZO INT NOT NULL,
    STATUS ENUM('PLANEJADO', 'EM EXECUÇÃO', 'ABORTADO', 'FINALIZADO') NOT NULL,
    ID_RESPONSAVEL INT NOT NULL,
    ID_PROJETO INT NOT NULL,
    FOREIGN KEY (ID_RESPONSAVEL) REFERENCES tb_responsavel_tarefa(ID),
    FOREIGN KEY (ID_PROJETO) REFERENCES tb_projetos(ID)
);

SELECT * FROM TB_TAREFAS;

CREATE TABLE USER(
	ID INT PRIMARY KEY AUTO_INCREMENT,
    USUARIO VARCHAR(50) NOT NULL UNIQUE,
    SENHA VARCHAR(300) NOT NULL
    
);

select * from user;

SELECT RT.ID, RT.NOME, T.titulo as nomeProjeto, T.STATUS FROM TB_RESPONSAVEL_TAREFA RT JOIN TB_TAREFAS T ON (RT.ID = T.ID_RESPONSAVEL);
drop database desafio;

SELECT T.titulo as nome_tarefa, T.status FROM TB_PROJETOS P JOIN TB_TAREFAS T ON (P.ID = T.ID_PROJETO)
        WHERE T.STATUS != 'FINALIZADO' AND T.STATUS != 'ABORTADO' and p.id = 1 LIMIT 1;