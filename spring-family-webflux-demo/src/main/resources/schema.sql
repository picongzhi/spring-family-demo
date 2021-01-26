DROP TABLE T_COFFEE IF EXISTS;
DROP TABLE T_ORDER IF EXISTS;
DROP TABLE T_ORDER_COFFEE IF EXISTS;

CREATE TABLE T_COFFEE
(
    id          BIGINT AUTO_INCREMENT,
    name        VARCHAR(255),
    price       BIGINT,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE T_ORDER
(
    id          BIGINT AUTO_INCREMENT,
    customer    VARCHAR(255),
    state       INTEGER NOT NULL,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE T_ORDER_COFFEE
(
    coffee_order_id BIGINT NOT NULL,
    items_id        BIGINT NOT NULL
);
