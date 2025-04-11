CREATE TABLE IF NOT EXISTS PAYMENT_TYPE (
    ID          SERIAL PRIMARY KEY,
    NAME        VARCHAR(100) NOT NULL,
    DESCRIPTION TEXT,
    CREATE_TIME TIMESTAMP    NOT NULL DEFAULT NOW(),
    UPDATE_TIME TIMESTAMP    NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE PAYMENT_TYPE IS 'Tipo de pagamento';
COMMENT ON COLUMN PAYMENT_TYPE.ID IS 'Id do tipo de pagamento';
COMMENT ON COLUMN PAYMENT_TYPE.NAME IS 'Nome do tipo de pagamento';
COMMENT ON COLUMN PAYMENT_TYPE.DESCRIPTION IS 'Descrição do tipo de pagamento';

ALTER TABLE PAYMENT_TYPE
ADD CONSTRAINT UQ_PAYMENT_TYPE_NAME UNIQUE (NAME);

INSERT INTO PAYMENT_TYPE (NAME, DESCRIPTION)
VALUES ('A Vista', 'Despesa e/ou Receita paga á vista');

INSERT INTO PAYMENT_TYPE (NAME, DESCRIPTION)
VALUES ('Parcelado', 'Despesa e/ou Receita paga parcelada');
