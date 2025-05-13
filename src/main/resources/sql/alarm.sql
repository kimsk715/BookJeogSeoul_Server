create table tbl_event
(
    id         bigint auto_increment
        primary key,
    year       int          null,
    month      int          null,
    event_type varchar(255) not null,
    event_text varchar(255) null
);