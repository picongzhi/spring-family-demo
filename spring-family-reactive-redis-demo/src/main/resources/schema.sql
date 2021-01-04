DROP TABLE t_coffee IF EXISTS;

CREATE TABLE t_coffee
(
    id          BIGINT AUTO_INCREMENT,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    name        VARCHAR(255),
    price       BIGINT,
    PRIMARY KEY (id)
);