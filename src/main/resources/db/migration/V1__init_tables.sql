CREATE TABLE role
(
    id   serial         NOT NULL,
    code character(16)  NOT NULL,
    name character(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user
(
    id       bigserial         NOT NULL,
    role_id  integer        NOT NULL
        CONSTRAINT fk_user_role
            REFERENCES role (id)
            ON UPDATE CASCADE
            ON DELETE NO ACTION,
    login    character(128) NOT NULL,
    password character(128) NOT NULL,
    name     character(128) NOT NULL,
    rating   numeric(5, 4),
    PRIMARY KEY (id)
);

CREATE TABLE occupation
(
    id        bigserial      NOT NULL,
    parent_id integer,
    name      character(128) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (parent_id, id)
);

CREATE TABLE profile
(
    id            bigserial  NOT NULL,
    user_id       integer NOT NULL
        CONSTRAINT fk_profile_user
            REFERENCES user (id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
    occupation_id integer NOT NULL
        CONSTRAINT fk_profile_occupation
            REFERENCES occupation (id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
    PRIMARY KEY (id)
);
CREATE INDEX user_name
    ON user (name);
CREATE INDEX user_rating
    ON user (rating);
CREATE INDEX occupation_name
ON occupation (name);
CREATE UNIQUE INDEX profile_user_occupation_uindex
    ON profile (user_id, occupation_id);

CREATE TABLE credential
(
    id      bigserial        NOT NULL,
    user_id integer       NOT NULL
        CONSTRAINT fk_credential_user
            REFERENCES user (id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
    code    character(16) NOT NULL,
    value   character(128),
    name    character(128),
    PRIMARY KEY (id),
    UNIQUE (user_id, code)
);

CREATE TABLE offer
(
    id            bigserial      NOT NULL,
    title         character(128) NOT NULL,
    description   character(256),
    bid_id      integer,
    offer_status  integer DEFAULT 1,
    price         numeric(9, 2)   DEFAULT 0,  -- цена предложения
    accepted_bid_id integer,
    user_creator_id integer       NOT NULL
        CONSTRAINT fk_offer_user_creator
            REFERENCES user (id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
    user_executor_id integer
        CONSTRAINT fk_offer_user_executor
            REFERENCES user (id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
    occupation_id integer        NOT NULL
        CONSTRAINT fk_offer_occupation
            REFERENCES occupation (id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_end timestamp,
    PRIMARY KEY (id)
);
CREATE UNIQUE INDEX offer_accepted_bid_id
    ON offer (accepted_bid_id);

-- заявка на исполнение заказа (вместо order)
CREATE TABLE bid
(
    id            bigserial        NOT NULL,
    price         numeric(9, 2)   DEFAULT 0,  -- цена предложения
    date_beg      date,                       -- дата ввода
    date_end      date,                       -- срок жизни
    user_id       integer          NOT NULL   -- пользователь
    CONSTRAINT fk_bid_user
        REFERENCES user (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    offer_id      integer          NOT NULL  -- id заявки
    CONSTRAINT fk_bid_offer
        REFERENCES offer (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    PRIMARY KEY  (id)
);

ALTER TABLE offer
    ADD CONSTRAINT fk_offer_bid_id
    FOREIGN KEY (accepted_bid_id)
    REFERENCES bid (id)
    ON UPDATE NO ACTION
   ON DELETE NO ACTION;
