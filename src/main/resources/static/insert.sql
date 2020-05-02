insert
into orders(placed, time)
values (false, null),
       (false, null),
       (false, null);

insert
into users
values ('admin', '1234', 'admin@example.com', false, true, 1),
       ('bill', '4321', 'bill@example.com', false, false, 2),
       ('tom', '1423', 'tom@example.com', true, false, 3);

insert
into books
values ('9780131872486', 'Thinking in Java (Fourth Edition)', 'Bruce Eckel', 'English', 'Prentice Hall PTR', '2006-02-20', 51800, 150),
       ('9781491946008', 'Fluent Python', 'Luciano Ramalho', 'English', 'O\'Reilly Media, Inc, USA', '2015-08-28', 33790, 150);
