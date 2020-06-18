delimiter //
drop function if exists `OrderMatchKeyword`;
create function `OrderMatchKeyword`(`order` int, `keyword` varchar(255))
returns bool
reads sql data
begin
    declare `pattern` varchar(511);
    declare `order_item_match` bool;
    declare `order_info_match` bool;
    set `pattern` = concat('%', `keyword`, '%');
    set `order_item_match` = exists(
            select *
            from `order_items` join `books` on `book` = `id`
            where `order_items`.`order` = `order` and
                  (`title` like pattern or `author` like `pattern` or `press` like `pattern`)
    );
    select `username` like pattern
    into `order_info_match`
    from `orders` join `users` on `user` = `users`.`id`
    where `orders`.`id` = `order`;
    return `order_item_match` or `order_info_match`;
end//
delimiter ;

delimiter //
drop procedure if exists `OrderSearch`;
create procedure `OrderSearch`(`time_placed_start` varchar(255), `time_placed_end` varchar(255),
                               `page` int, `size` int)
begin
    declare `offset` int;
    set `offset` = `page` * `size`;
    select *
    from `orders`
    where `time_placed` >= `time_placed_start` and
          `time_placed` <= `time_placed_end`
    order by `id`
    limit `offset`, `size`;
end//
delimiter ;

delimiter //
drop procedure if exists `OrderSearchCount`;
create procedure `OrderSearchCount`(`time_placed_start` varchar(255), `time_placed_end` varchar(255))
begin
    select count(*)
    from `orders`
    where `time_placed` >= `time_placed_start` and
          `time_placed` <= `time_placed_end`;
end//
delimiter ;

delimiter //
drop procedure if exists `OrderFuzzySearch`;
create procedure `OrderFuzzySearch`(`keyword` varchar(255), `time_placed_start` varchar(255),
                                    `time_placed_end` varchar(255), `page` int, `size` int)
begin
    declare `offset` int;
    set `offset` = `page` * `size`;
    select *
    from `orders`
    where `OrderMatchKeyword`(`id`, `keyword`) and
          `time_placed` >= `time_placed_start` and
          `time_placed` <= `time_placed_end`
    order by `id`
    limit `offset`, `size`;
end//
delimiter ;

delimiter //
drop procedure if exists `OrderFuzzySearchCount`;
create procedure `OrderFuzzySearchCount`(`keyword` varchar(255),
                                         `time_placed_start` varchar(255), `time_placed_end` varchar(255))
begin
    select count(*)
    from `orders`
    where `OrderMatchKeyword`(`id`, `keyword`) and
          `time_placed` >= `time_placed_start` and
          `time_placed` <= `time_placed_end`;
end//
delimiter ;

delimiter //
drop procedure if exists `OrderSearchWithUserId`;
create procedure `OrderSearchWithUserId`(`user` int, `time_placed_start` varchar(255),
                                       `time_placed_end` varchar(255), `page` int, `size` int)
begin
    declare `offset` int;
    set `offset` = `page` * `size`;
    select *
    from `orders`
    where `orders`.`user` = `user` and
          `time_placed` >= `time_placed_start` and
          `time_placed` <= `time_placed_end`
    order by `id`
    limit `offset`, `size`;
end//
delimiter ;

delimiter //
drop procedure if exists `OrderSearchWithUserIdCount`;
create procedure `OrderSearchWithUserIdCount`(`user` int, `time_placed_start` varchar(255), `time_placed_end` varchar(255))
begin
    select count(*)
    from `orders`
    where `orders`.`user` = `user` and
          `time_placed` >= `time_placed_start` and
          `time_placed` <= `time_placed_end`;
end//
delimiter ;

delimiter //
drop procedure if exists `OrderFuzzySearchWithUserId`;
create procedure `OrderFuzzySearchWithUserId`(`user` int, `keyword` varchar(255), `time_placed_start` varchar(255),
                                    `time_placed_end` varchar(255), `page` int, `size` int)
begin
    declare `offset` int;
    set `offset` = `page` * `size`;
    select *
    from `orders`
    where `orders`.`user` = `user` and
          `OrderMatchKeyword`(`id`, `keyword`) and
          `time_placed` >= `time_placed_start` and
          `time_placed` <= `time_placed_end`
    order by `id`
    limit `offset`, `size`;
end//
delimiter ;

delimiter //
drop procedure if exists `OrderFuzzySearchWithUserIdCount`;
create procedure `OrderFuzzySearchWithUserIdCount`(`user` int, `keyword` varchar(255),
                                         `time_placed_start` varchar(255), `time_placed_end` varchar(255))
begin
    select count(*)
    from `orders`
    where `orders`.`user` = `user` and
          `OrderMatchKeyword`(`id`, `keyword`) and
          `time_placed` >= `time_placed_start` and
          `time_placed` <= `time_placed_end`;
end//
delimiter ;
