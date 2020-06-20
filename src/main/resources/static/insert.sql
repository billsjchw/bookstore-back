insert into `books`(`isbn`, `title`, `author`, `lang`, `press`, `date`, `price`, `stock`)
values ('9780131872486', 'Thinking in Java (Fourth Edition)', 'Bruce Eckel', 'English', 'Prentice Hall PTR', '2006-02-20', 51800, 150),
       ('9781449355739', 'Learning Python (Fifth Edition)', 'Mark Lutz', 'English', 'O\'Reilly Media, Inc, USA', '2013-06-30', 48357, 200),
       ('9780262033848', 'Introduction to Algorithms (Third Edition)', 'Thomas H. Cormen; Charles E. Leiserson; Ronald L. Rivest', 'English', 'MIT Press', '2009-07-31', 169100, 150),
       ('9781119366447', 'Professional JavaScript for Web Developers (Fourth Edition)', 'Matt Frisbie', 'English', 'Wrox Press', '2019-09-01', 44200, 200),
       ('9781491946008', 'Fluent Python', 'Luciano Ramalho', 'English', 'O\'Reilly Media, Inc, USA', '2015-08-28', 33790, 150),
       ('9780134190440', 'The Go Programming Language', 'Alan A. a. Donovan; Brian W. Kernighan', 'English', 'Addison-Wesley Professional', '2015-10-26', 49600, 200),
       ('9780321563842', 'The C++ Programming Language (Fourth Edition)', 'Bjarne Stroustrup', 'English', 'Addison-Wesley Professional', '2013-01-04', 93300, 150),
       ('9780073523323', 'Database System Concepts (Sixth Edition)', 'Abraham Silberchatz; Henry F. Korth; S. Sudarshan', 'English', 'McGraw Hill Education', '2010-01-27', 248700, 200),
       ('9780321573513', 'Algorithms (Fourth Edition)', 'Robert Sedgewick; Kevin Wayne', 'English', 'Addison-Wesley Professional', '2011-03-09', 114000, 150),
       ('9780321295354', 'Algorithm Design', 'Jon Kleinberg; Eva Tardos', 'English', 'Pearson', '2005-03-26', 181700, 250);

insert into `users`(`username`, `password`, `first_name`, `last_name`, `email`, `enabled`)
values ('bill', '1234', 'William', 'Adams', 'bill@example.com', true),
       ('tom', '1234', 'Thomas', 'Clark', 'tom@example.com', false);

insert into `carts`(`user`)
values (2), (3);

insert into `orders`(`user`, `time_placed`, `consignee_first_name`, `consignee_last_name`, `consignee_address`, `consignee_phone`, `payment_method`)
values (2, '2020-05-01 12:00:00', 'William', 'Adams', 'Shanghai Jiao Tong University', '12312341234', 'COD'),
       (3, '2020-01-01 12:00:00', 'Thomas', 'Clark', 'Shanghai Jiao Tong University', '12312341234', 'ONLINE');

insert into order_items(`order`, `book`, `amount`, `price`)
values (1, 1, 3, 51800),
       (1, 3, 5, 169100),
       (2, 5, 2, 33790);
