drop table if exists fone;
drop table if exists itens_pedido;
drop table if exists pedido;
drop table if exists pessoa;
drop table if exists produto;
drop table if exists formapgto;


create table produto(
	prod_id serial 		primary key,
	pro_nome 			varchar(60),
	pro_preco 			float,
	foto 				varchar(80),
	caminho 			varchar(80)
);


create table pessoa
(
pes_id 				serial primary key,
pes_nome 			varchar(60),
pes_permissao		varchar(60),
pes_cpf 			char(14),
pes_rg 				varchar(20),
pes_data_nasc 		date,
pes_rua 			varchar(60),
pes_bairro 			varchar(30),
pes_cidade 			varchar(30),
pes_uf 				char(2),
pes_cep 			integer,
pes_email 			varchar(40),
pes_senha 			varchar(32)
);

create table fone(
fon_id			serial	primary key,
pes_id			integer,
fon_numero		varchar(20),
fon_descricao	varchar(30),
constraint rel_pessoa_fone foreign key (pes_id) references pessoa (pes_id)
);


create table formapgto (

fpg_id  				serial primary key,
fpg_descricao 			varchar(20),
fpg_num_max_parc  		integer,
fpg_num_padrao_parc 	integer,
fpg_intervalo_dias 		integer,
fpg_percentual_acres 	float 
);


create table pedido (
ped_id				serial	primary key,
pes_id				integer	not null,
fpg_id				integer	not null,
ped_dataEmissao		timestamp,
ped_status			varchar(20),
ped_dataAutorizacao timestamp,
ped_total			float,
ped_desconto		float,
constraint rel_pessoa_pedido foreign key (pes_id) references pessoa(pes_id), 
constraint rel_formapgto_pedido foreign key (fpg_id) references formapgto(fpg_id)
);

create table itens_pedido (
ipe_id			serial	primary key,
ped_id			integer	not null,
pro_id			integer	not null,
ipe_qtde		float,
ipe_valorUnit	float,
ipe_subtotal	float,
constraint rel_pedido_itenspedido foreign key (ped_id) references pedido(ped_id), 
constraint rel_produto_itenspedido foreign key (pro_id) references produto(prod_id)
);