INSERT INTO role (id, code, name)
VALUES (1, 'ROLE_ADMIN', 'Админ'),
       (2, 'ROLE_USER', 'Пользователь');


-- пароль: 123
INSERT INTO user (id, role_id, login, password, name, rating)
VALUES (1, 2, 'ivan', '$2a$10$3ivPJN8MyXC/bb0y87FxzuYkzMByRuj555b4DfS3x7EtWM4tzX5u.', 'Ivan Petrov', 3.4567),
       (2, 2, 'petr', '$2a$10$3ivPJN8MyXC/bb0y87FxzuYkzMByRuj555b4DfS3x7EtWM4tzX5u.', 'Petr Ivanov', 4.0123),
       (3, 2, 'andrey', '$2a$10$3ivPJN8MyXC/bb0y87FxzuYkzMByRuj555b4DfS3x7EtWM4tzX5u.', 'Petr Ivanov', 5.9999);


INSERT INTO occupation (id, parent_id, name)
VALUES (1, NULL, 'Строительство'),
       (2, 1, 'Кладка кирпича/блока'),
       (3, 1, 'Монтаж окон'),
       (4, NULL, 'Транспорт/перевозки'),
       (5, 4, 'Спецтехника'),
       (6, 5, 'Услуги манипулятора'),
       (7, NULL, 'Ремонт'),
       (8, 7, 'Сантехнические работы'),
       (9, 7, 'Ремонт мебели'),
       (10, 7, 'Ремонт под ключ'),
       (11, 10, 'Кухня'),
       (12, 10, 'Санузел');

INSERT INTO offer (id, title, description, user_creator_id, occupation_id)
VALUES (1, 'Установка плит', '5 плит, высота 4 м', 1, 6),
       (2, 'Предложение_2', 'Описание_2', 2, 3),
       (3, 'Предложение_3', 'Описание_3', 2, 5),
       (4, 'Предложение_4', 'Описание_4', 1, 4),
       (5, 'Установка плит', '5 плит, высота 4 м', 1, 6),
       (6, 'Предложение_5', 'Описание_5', 1, 1);

INSERT INTO profile (user_id, occupation_id)
VALUES (2, 1),
       (2, 2),
       (2, 3),
       (1, 1),
       (1, 4),
       (1, 5),
       (1, 6);


INSERT INTO credential (user_id, code, value, name)
VALUES (2, 'phone', '+79001238070', NULL),
       (2, 'sdfsd', 'asdfsfadf', NULL);

-- для теста акцептованных откликов

INSERT INTO offer (id, title, description, user_creator_id, occupation_id)
VALUES (7, 'Установка плит', '5 плит, высота 4 м', 2, 6),
       (8, 'Предложение_5', 'Описание_5', 3, 1);

INSERT INTO bid (id, user_id, offer_id, price)
VALUES (1, 1, 1, 10000.00),
       (2, 2, 1, 12000.00),
       (3, 2, 2, 15000.00),
       (4, 1, 2, 13000.00),
       (5, 3, 2, 14000.00),
       (6, 1, 3, 16000.00),
       (7, 1, 7, 12000.00),
       (8, 1, 8, 12000.00);

UPDATE offer SET accepted_bid_id = 7 WHERE id = 7;
UPDATE offer SET accepted_bid_id = 8 WHERE id = 8;