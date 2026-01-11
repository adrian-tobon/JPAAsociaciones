create database db_jpa_relationship;

create table db_jpa_relationship.clients (id bigint not null auto_increment, lastname varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table db_jpa_relationship.invoices (client_id bigint, id bigint not null auto_increment, total bigint, description varchar(255), primary key (id)) engine=InnoDB;
alter table db_jpa_relationship.invoices add constraint FK9ioqm804urbgy986pdtwqtl0x foreign key (client_id) references clients (id);

insert into db_jpa_relationship.clients (name, lastname) values ('Pepe','Doe');
insert into db_jpa_relationship.clients (name, lastname) values ('Luis','Perez');
insert into db_jpa_relationship.clients (name, lastname) values ('Jorge','Perez');
insert into db_jpa_relationship.clients (name, lastname) values ('Juan','Garcia');
insert into db_jpa_relationship.clients (name, lastname) values ('Jose','Artua');
insert into db_jpa_relationship.clients (name, lastname) values ('Leon','Gomez');
insert into db_jpa_relationship.clients (name, lastname) values ('Ian','Neeson');
insert into db_jpa_relationship.clients (name, lastname) values ('Lan','Caster');


select * from db_jpa_relationship.invoices;

select * from db_jpa_relationship.clients;

alter table db_jpa_relationship.invoices drop constraint FK9fbt36pf6epispvci1fnkvd4s;

alter table db_jpa_relationship.invoices drop column id_client;

delete from db_jpa_relationship.invoices where id = 6;

alter table db_jpa_relationship.invoices rename column client_id to id_client;

create table db_jpa_relationship.addresses (id bigint not null auto_increment, number integer, street varchar(255), primary key (id)) engine=InnoDB;
create table db_jpa_relationship..clients_addresses (client_id bigint not null, addresses_id bigint not null) engine=InnoDB;
alter table db_jpa_relationship.clients_addresses add constraint UKd9liq56jlec2x8nipbxxd705n unique (addresses_id)
alter table db_jpa_relationship.clients_addresses add constraint FKejf92g3ybg34aogu7o2tbtgca foreign key (addresses_id) references addresses (id)
alter table db_jpa_relationship.clients_addresses add constraint FK12sx33jn6tq0mgjvmic1696cs foreign key (client_id) references clients (id)


create table db_jpa_relationship.addresses (id bigint not null auto_increment, number integer, street varchar(255), client_id bigint, primary key (id)) engine=InnoDB
alter table db_jpa_relationship.addresses add constraint FKrf3c1s9gxxx0wubkv5maokv9y foreign key (client_id) references clients (id)