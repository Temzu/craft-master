create table offers
(
    id   bigserial primary key,
    name varchar(255)
);

insert into offers (name)
values ('Offer-1'),
       ('Offer-2'),
       ('Offer-3'),
       ('Offer-4'),
       ('Offer-5'),
       ('Offer-6');
