insert into `roles`(`name`)
values ('ADMIN');

insert into `authorities`(`name`)
values ('BOOK_ADMIN'), ('USER_ADMIN'), ('ORDER_ADMIN');

insert into `role_authority`
values (1, 1), (1, 2), (1, 3);

insert into `users`(`username`, `password`, `first_name`, `last_name`, `email`, `enabled`)
values ('admin', '1234', 'bookstore', 'administrator', 'admin@example.com', true);

insert into `user_role`
values (1, 1);

insert into `carts`(`user`)
values (1);
