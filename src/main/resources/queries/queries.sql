create database db_jpa_relationship;

create table db_jpa_relationship.clients (id bigint not null auto_increment, lastname varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table db_jpa_relationship.invoices (client_id bigint, id bigint not null auto_increment, total bigint, description varchar(255), primary key (id)) engine=InnoDB;
alter table db_jpa_relationship.invoices add constraint fk_clientid foreign key (client_id) references clients (id);

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