INSERT INTO role (id, code, name)
VALUES (1, 'ROLE_ADMIN', 'Админ'),
       (2, 'ROLE_USER', 'Пользователь');


-- пароль: 123
INSERT INTO user (id, role_id, login, password, name, rating)
    VALUES (1, 2, 'ivan', '$2a$10$3ivPJN8MyXC/bb0y87FxzuYkzMByRuj555b4DfS3x7EtWM4tzX5u.', 'Ivan Petrov', 3.4567),
       (2, 2, 'petr', '$2a$10$3ivPJN8MyXC/bb0y87FxzuYkzMByRuj555b4DfS3x7EtWM4tzX5u.', 'Petr Ivanov', 4.0123);

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


INSERT INTO offer (title, description, user_id, occupation_id)
VALUES ('Установка плит', '5 плит, высота 4 м', 1, 6),
       ('Предложение_2', 'Описание_2', 2, 3),
       ('Предложение_3', 'Описание_3', 2, 5),
       ('Предложение_4', 'Описание_4', 1, 4);

INSERT INTO profile (id, user_id, occupation_id)
VALUES (1, 2, 3),
       (2, 2, 6);

INSERT INTO credential (user_id, code, value, name)
VALUES (2, 'phone', '+79001238070', NULL),
       (2, 'sdfsd', 'asdfsfadf', NULL);

INSERT INTO offer (title, description, user_id, occupation_id)
VALUES ('Установка плит', '5 плит, высота 4 м', 1, 6);

