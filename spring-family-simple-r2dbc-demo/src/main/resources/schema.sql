DROP TABLE t_coffee IF EXISTS;

CREATE TABLE t_coffee
(
    id          BIGINT AUTO_INCREMENT,
    name        VARCHAR(255),
    price       BIGINT,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    PRIMARY KEY (id)
);