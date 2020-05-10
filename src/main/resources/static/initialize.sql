insert into `roles`
values ('ADMIN');

insert into `authorities`
values ('BOOK_ADMIN'), ('USER_ADMIN');

insert into `role_authority`
values ('ADMIN', 'BOOK_ADMIN'), ('ADMIN', 'USER_ADMIN');

insert into `users`
values ('admin', '1234', 'bookstore', 'administrator', 'admin@example.com', true);

insert into `user_role`
values ('admin', 'ADMIN');

insert into `carts`(`user`)
values ('admin');
