INSERT INTO role (id, code, name)
VALUES (1, 'ROLE_ADMIN', 'Админ'),
       (2, 'ROLE_USER', 'Пользователь');


-- пароль: 123
INSERT INTO user (id, role_id, login, password, name, rating)
VALUES (1, 2, 'ivan', '$2a$10$3ivPJN8MyXC/bb0y87FxzuYkzMByRuj555b4DfS3x7EtWM4tzX5u.', 'Ivan Petrov', 3.4567),
       (2, 2, 'petr', '$2a$10$3ivPJN8MyXC/bb0y87FxzuYkzMByRuj555b4DfS3x7EtWM4tzX5u.', 'Petr Ivanov', 4.0123);

INSERT INTO service (id, parent_id, name)
VALUES (1, NULL, 'Строительство'),
       (2, 1, 'Кладка кирпича/блока'),
       (3, 1, 'Монтаж окон'),
       (4, NULL, 'Транспорт/перевозки'),
       (5, 4, 'Спецтехника'),
       (6, 5, 'Услуги манипулятора');

INSERT INTO profile (id, user_id, service_id)
VALUES (1, 2, 3),
       (2, 2, 6);
INSERT INTO credential (user_id, code, value, name)
VALUES (2, 'phone', '+79001238070', NULL);

INSERT INTO offer (title, description, user_id, service_id)
VALUES ('Установка плит', '5 плит, высота 4 м', 1, 6);

