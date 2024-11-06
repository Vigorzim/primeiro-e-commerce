-- todas as tabelas 

create table usuario (

id_cliente int primary key,
id_pedido int,
nome varchar(60),
email varchar(100) not null,
senha varchar(100) not null

);

create table pedido (

id_pedido int primary key,
id_usuario int not null,
foreign key (usuario_id) references usuario(id)

);

create table item_pedido (

id_pedido int not null,
id_item int not null,
foreign key (id_pedido, id_item),
foreign key (id_pedido) references pedido(id),
foreign key (id_item) references item(id)

);

create table item (

id_item int primary key,
nome varchar(100) not null,
preco decimal(10, 2) not null,
estoque int not null

);

create table pagamento (

id_pagamento int not null,
forma_pagamento_id int not null,


);

create table forma_pgto (

id_forma_pgto int primary key,


);

