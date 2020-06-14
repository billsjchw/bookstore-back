set FOREIGN_KEY_CHECKS = 0;

drop table if exists `books`;
create table `books` (
    `id` int auto_increment,
    `isbn` varchar(15) not null,
    `title` varchar(63) not null,
    `author` varchar(255) not null,
    `lang` varchar(63) not null,
    `press` varchar(255) not null,
    `date` date not null,
    `price` int not null check (`price` >= 0),
    `stock` int not null check (`stock` >= 0),
    primary key (`id`)
);

drop table if exists `users`;
create table `users` (
    `id` int auto_increment,
    `username` varchar(31) not null,
    `password` varchar(31) not null,
    `first_name` varchar(63) not null,
    `last_name` varchar(63) not null,
    `email` varchar(63) not null,
    `enabled` boolean not null,
    primary key (`id`),
    unique key (`username`)
);

drop table if exists `roles`;
create table `roles` (
    `id` int auto_increment,
    `name` varchar(31) not null,
    primary key (`id`)
);

drop table if exists `authorities`;
create table `authorities` (
    `id` int auto_increment,
    `name` varchar(31) not null,
    primary key (`id`)
);

drop table if exists `user_role`;
create table `user_role` (
    `user` int,
    `role` int,
    primary key (`user`, `role`),
    foreign key (`user`) references `users`(`id`) on delete cascade,
    foreign key (`role`) references `roles`(`id`) on delete cascade
);

drop table if exists `role_authority`;
create table `role_authority` (
    `role` int,
    `authority` int,
    primary key (`role`, `authority`),
    foreign key (`role`) references `roles`(`id`) on delete cascade,
    foreign key (`authority`) references `authorities`(`id`) on delete cascade
);

drop table if exists `carts`;
create table `carts` (
    `id` int auto_increment,
    `user` int not null,
    primary key (`id`),
    unique key (`user`),
    foreign key (`user`) references `users`(`id`) on delete cascade
);

drop table if exists `cart_items`;
create table `cart_items` (
    `cart` int,
    `book` int,
    `amount` int not null check (`amount` > 0),
    `active` bool not null,
    primary key (`cart`, `book`),
    foreign key (`cart`) references `carts`(`id`) on delete cascade,
    foreign key (`book`) references `books`(`id`) on delete cascade
);

drop table if exists `orders`;
create table `orders` (
    `id` int auto_increment,
    `user` int not null,
    `status` enum('UNPAID', 'DELIVERING', 'DONE', 'CANCELLED') not null,
    `time_placed` timestamp not null,
    `time_paid` timestamp null,
    `time_finished` timestamp null,
    `consignee_address` varchar(255) not null,
    `consignee_phone` varchar(31) not null,
    `consignee_fist_name` varchar(63) not null,
    `consignee_last_name` varchar(63) not null,
    primary key (`id`),
    foreign key (`user`) references `users`(`id`) on delete cascade
);

drop table if exists `order_items`;
create table `order_items` (
    `order` int,
    `book` int,
    `amount` int not null check (`amount` > 0),
    primary key (`order`, `book`),
    foreign key (`order`) references `orders`(`id`) on delete cascade,
    foreign key (`book`) references `books`(`id`) on delete cascade
);

set FOREIGN_KEY_CHECKS = 1;
