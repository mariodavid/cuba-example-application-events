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
);
