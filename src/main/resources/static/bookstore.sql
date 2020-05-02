drop table if exists users;
drop table if exists items;
drop table if exists orders;
drop table if exists books;

create table books (
    isbn varchar(15) primary key,
    title varchar(63) not null,
    author varchar(255) not null,
    lang varchar(63) not null,
    press varchar(255) not null,
    `date` date not null,
    price int not null check (price >= 0),
    stock int not null check (stock >= 0)
);

create table orders (
    id bigint auto_increment primary key,
    placed boolean not null,
    time timestamp,
    check (not placed or time is not null)
);

create table items (
    `order` bigint,
    book varchar(15),
    num int not null,
    primary key (`order`, book),
    foreign key (`order`) references orders(id) on delete cascade,
    foreign key (book) references books(isbn) on delete cascade
);

create table users (
    username varchar(31) primary key,
    password varchar(31) not null,
    email varchar(63) not null,
    banned boolean not null,
    admin boolean not null,
    cart bigint not null,
    foreign key (cart) references orders(id) on delete cascade
);
