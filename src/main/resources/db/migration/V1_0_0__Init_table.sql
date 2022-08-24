CREATE TABLE IF NOT EXISTS catalogs
(
    id           SERIAL,
    catalog_name VARCHAR(50) NOT NULL UNIQUE,
    CONSTRAINT pk_catalog_id PRIMARY KEY (id)
);

INSERT INTO catalogs (id, catalog_name)
VALUES (1, 'Java'),
       (2, 'JavaScript'),
       (3, 'Spring Framework'),
       (4, 'Spring Web'),
       (5, 'Spring Data'),
       (6, 'Lombok');