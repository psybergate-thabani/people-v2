--liquibase formatted sql
--changeset thabani:create_employee_table
CREATE TABLE employee(
    id                  UUID PRIMARY KEY,
    version             BIGINT,
    deleted             BOOLEAN NOT NULL,
    created_date        TIMESTAMP,
    created_by          VARCHAR(255),
    last_modified_by    VARCHAR(255),
    last_modified_date  TIMESTAMP,
    employee_code       VARCHAR(255) NOT NULL UNIQUE,
    full_name           VARCHAR(255) NOT NULL,
    last_name           VARCHAR(255) NOT NULL,
    email               VARCHAR(255) NOT NULL,
    physical_address    VARCHAR(255) NOT NULL,
    postal_address      VARCHAR(255) NOT NULL,
    start_date          TIMESTAMP NOT NULL,
    end_date            TIMESTAMP,
    occupation          VARCHAR(255) NOT NULL,
    status              VARCHAR(25) NOT NULL
);