--liquibase formatted sql
--changeset lewis_florez:R1.11

create table spring_boot_api.person (
    id                 serial PRIMARY KEY,
    document           varchar(12)  not null,
    first_name         varchar(100) not null,
    second_name        varchar(100) null,
    first_surname      varchar(100) not null,
    second_surname     varchar(100) null,
    gender             varchar(1)   not null,
    birthday           date         not null,
    address            varchar(100) not null,
    telephone_number   varchar(10)   null,
    cellphone_number   varchar(20)   null
    );