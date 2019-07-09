drop table if exists Item;
drop table if exists PromoOrder;
drop table if exists PromoOrder_Item;
drop table if exists PromoOrderHolder;
drop table if exists PromoOrderHolder_PromoOrder;

create table if not exists Item (
    id identity,
    ean varchar(15) not null ,
    itemName varchar(255) not null ,
    category varchar(50)
);

create table if not exists PromoOrder (
    id identity ,
    createdAt timestamp not null
);

create table if not exists PromoOrder_Item (
   promoOrder bigint not null ,
   item bigint not null
);

alter table PromoOrder_Item
    add foreign key (promoOrder) references PromoOrder(id);
alter table PromoOrder_Item
    add foreign key (item) references Item(id);

create table if not exists PromoOrderHolder (
    id identity not null ,
    name varchar(55) not null ,
    placedAt timestamp not null
);

create table if not exists PromoOrderHolder_PromoOrder (
    promoOrderHolder bigint not null ,
    promoOrder bigint not null
) ;

alter table PromoOrderHolder_PromoOrder
    add foreign key (promoOrderHolder) references PromoOrderHolder(id);
alter table PromoOrderHolder_PromoOrder
    add foreign key (promoOrder) references PromoOrder(id);



