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

INSERT INTO t_coffee (name, price, create_time, update_time)
VALUES ('espresso', 2000, now(), now());
INSERT INTO t_coffee (name, price, create_time, update_time)
VALUES ('latte', 2500, now(), now());
INSERT INTO t_coffee (name, price, create_time, update_time)
VALUES ('capuccino', 2500, now(), now());
INSERT INTO t_coffee (name, price, create_time, update_time)
VALUES ('mocha', 3000, now(), now());
INSERT INTO t_coffee (name, price, create_time, update_time)
VALUES ('macchiato', 3000, now(), now());