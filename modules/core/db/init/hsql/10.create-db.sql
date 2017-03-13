-- begin CEAE_CUSTOMER
create table CEAE_CUSTOMER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    FIRST_NAME varchar(255),
    CUSTOMER_TYPE varchar(50),
    --
    primary key (ID)
)^
-- end CEAE_CUSTOMER
-- begin CEAE_ORDER
create table CEAE_ORDER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ORDER_DATE date,
    --
    primary key (ID)
)^
-- end CEAE_ORDER
-- begin CEAE_NOTIFICATION
create table CEAE_NOTIFICATION (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_ID varchar(36),
    MESSAGE varchar(4000),
    ENTITY_CLASS varchar(255),
    ENTITY_ID varchar(36),
    ENTITY_CAPTION varchar(255),
    READ_ boolean,
    --
    primary key (ID)
)^
-- end CEAE_NOTIFICATION
-- begin CEAE_NOTIFICATION_SUBSCRIPTION
create table CEAE_NOTIFICATION_SUBSCRIPTION (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_ID varchar(36),
    ENTITY_CLASS varchar(255) not null,
    CONDITION_ varchar(4000) not null,
    --
    primary key (ID)
)^
-- end CEAE_NOTIFICATION_SUBSCRIPTION
