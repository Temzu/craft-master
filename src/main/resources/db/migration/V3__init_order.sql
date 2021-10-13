create table orders
(
    id   bigserial primary key,
    name varchar(255)
);

insert into orders (name)
values ('Order-1'),
       ('Order-2'),
       ('Order-3'),
       ('Order-4'),
       ('Order-5'),
       ('Order-6');
