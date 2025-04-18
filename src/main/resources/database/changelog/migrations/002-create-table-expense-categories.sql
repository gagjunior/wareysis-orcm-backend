CREATE TABLE IF NOT EXISTS EXPENSE_CATEGORY (
    USER_ID     INTEGER      NOT NULL,
    NAME        VARCHAR(100) NOT NULL,
    DESCRIPTION TEXT,
    CREATE_TIME TIMESTAMP DEFAULT NOW(),
    UPDATE_TIME TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (USER_ID, NAME),
    CONSTRAINT FK_USER_REGISTRATION FOREIGN KEY (USER_ID) REFERENCES USER_REGISTRATION(ID) ON DELETE CASCADE
);

CREATE INDEX IDX_EXPENSE_CATEGORY_USER_NAME ON EXPENSE_CATEGORY(USER_ID, NAME);

COMMENT ON TABLE EXPENSE_CATEGORY IS 'Categorias de despesas';
COMMENT ON COLUMN EXPENSE_CATEGORY.USER_ID IS 'Id do usuário';
COMMENT ON COLUMN EXPENSE_CATEGORY.NAME IS 'Nome da categoria';
COMMENT ON COLUMN EXPENSE_CATEGORY.DESCRIPTION IS 'Descrição da categoria';
COMMENT ON COLUMN EXPENSE_CATEGORY.CREATE_TIME IS 'Data e hora da criação da categoria';
COMMENT ON COLUMN EXPENSE_CATEGORY.UPDATE_TIME IS 'Data e hora da última alteração da categoria';

INSERT INTO EXPENSE_CATEGORY (USER_ID, NAME, DESCRIPTION)
VALUES (1, 'Alimentação', 'Despesas com alimentação');

INSERT INTO EXPENSE_CATEGORY (USER_ID, NAME, DESCRIPTION)
VALUES (1, 'Transporte', 'Despesas com transporte');

INSERT INTO EXPENSE_CATEGORY (USER_ID, NAME, DESCRIPTION)
VALUES (1, 'Educação', 'Despesas com educação');

INSERT INTO EXPENSE_CATEGORY (USER_ID, NAME, DESCRIPTION)
VALUES (1, 'Animais de Estimação', 'Despesas com animais de estimação');

INSERT INTO EXPENSE_CATEGORY (USER_ID, NAME, DESCRIPTION)
VALUES (1, 'Saúde', 'Despesas com saúde');

INSERT INTO EXPENSE_CATEGORY (USER_ID, NAME, DESCRIPTION)
VALUES (1, 'Viagens', 'Despesas com viagens');

INSERT INTO EXPENSE_CATEGORY (USER_ID, NAME, DESCRIPTION)
VALUES (1, 'Cabelo', 'Despesas com cabelo');

INSERT INTO EXPENSE_CATEGORY (USER_ID, NAME, DESCRIPTION)
VALUES (1, 'Compras', 'Despesas com compras');

INSERT INTO EXPENSE_CATEGORY (USER_ID, NAME, DESCRIPTION)
VALUES (1, 'Construção', 'Despesas com construção');

INSERT INTO EXPENSE_CATEGORY (USER_ID, NAME, DESCRIPTION)
VALUES (1, 'Contas', 'Despesas com contas');

INSERT INTO EXPENSE_CATEGORY (USER_ID, NAME, DESCRIPTION)
VALUES (1, 'Juros e Multas', 'Despesas com juros e multas');

INSERT INTO EXPENSE_CATEGORY (USER_ID, NAME, DESCRIPTION)
VALUES (1, 'Mercado', 'Despesas com mercado');

INSERT INTO EXPENSE_CATEGORY (USER_ID, NAME, DESCRIPTION)
VALUES (1, 'Seguros', 'Despesas com seguros');

INSERT INTO EXPENSE_CATEGORY (USER_ID, NAME, DESCRIPTION)
VALUES (1, 'Serviços', 'Despesas com serviços');

INSERT INTO EXPENSE_CATEGORY (USER_ID, NAME, DESCRIPTION)
VALUES (1, 'Vestuário', 'Despesas com vestuário');

INSERT INTO EXPENSE_CATEGORY (USER_ID, NAME, DESCRIPTION)
VALUES (1, 'Outras Despesas', 'Despesas diversas');


