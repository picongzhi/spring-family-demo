DROP TABLE t_coffee IF EXISTS;
DROP TABLE t_order IF EXISTS;
DROP TABLE t_oreder_coffee IF EXISTS;

CREATE TABLE t_coffee
(
    id          BIGINT AUTO_INCREMENT,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    name        VARCHAR(255),
    price       BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE t_order
(
    id          BIGINT AUTO_INCREMENT,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    customer    VARCHAR(255),
    state       INTEGER NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE t_order_coffee
(
    coffee_order_id BIGINT NOT NULL,
    items_id        BIGINT NOT NULL
);
