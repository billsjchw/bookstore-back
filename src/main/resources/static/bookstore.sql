drop table if exists `order_items`;
drop table if exists `orders`;
drop table if exists `order_statuses`;
drop table if exists `cart_items`;
drop table if exists `carts`;
drop table if exists `role_authority`;
drop table if exists `user_role`;
drop table if exists `authorities`;
drop table if exists `roles`;
drop table if exists `users`;
drop table if exists `books`;

create table `books` (
    `isbn` varchar(15),
    `title` varchar(63) not null,
    `author` varchar(255) not null,
    `language` varchar(63) not null,
    `press` varchar(255) not null,
    `date` date not null,
    `price` int not null check (`price` >= 0),
    `stock` int not null check (`stock` >= 0),
    primary key (`isbn`)
);

create table `users` (
    `username` varchar(31),
    `password` varchar(31) not null,
    `first_name` varchar(63) not null,
    `last_name` varchar(63) not null,
    `email` varchar(63) not null,
    `enabled` boolean not null,
    primary key (`username`)
);

create table `roles` (
    `name` varchar(31),
    primary key (`name`)
);

create table `authorities` (
    `name` varchar(31),
    primary key (`name`)
);

create table `user_role` (
    `user` varchar(31),
    `role` varchar(31),
    primary key (`user`, `role`),
    foreign key (`user`) references `users`(`username`) on delete cascade,
    foreign key (`role`) references `roles`(`name`) on delete cascade
);

create table `role_authority` (
    `role` varchar(31),
    `authority` varchar(31),
    primary key (`role`, `authority`),
    foreign key (`role`) references `roles`(`name`) on delete cascade,
    foreign key (`authority`) references `authorities`(`name`) on delete cascade
);

create table `carts` (
    `id` bigint auto_increment,
    `user` varchar(31) not null,
    primary key (`id`),
    foreign key (`user`) references `users`(`username`) on delete cascade
);

create table `cart_items` (
    `cart` bigint,
    `book` varchar(15),
    `amount` int check (`amount` > 0),
    primary key (`cart`, `book`),
    foreign key (`cart`) references `carts`(`id`) on delete cascade,
    foreign key (`book`) references `books`(`isbn`) on delete cascade
);

create table `orders` (
    `id` bigint auto_increment,
    `user` varchar(31) not null,
    `address` varchar(255) not null,
    `phone` varchar(31) not null,
    `name` varchar(127) not null,
    `status` enum('UNPAID', 'CANCELLED', 'DELIVERING', 'DONE') not null,
    `time_placed` timestamp not null,
    `time_paid` timestamp,
    `time_done` timestamp,
    primary key (`id`),
    foreign key (`user`) references `users`(`username`) on delete cascade
);

create table `order_items` (
    `order` bigint,
    `book` varchar(15),
    `amount` int check (`amount` > 0),
    primary key (`order`, `book`),
    foreign key (`order`) references `orders`(`id`) on delete cascade,
    foreign key (`book`) references `books`(`isbn`) on delete cascade
);
