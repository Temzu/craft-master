CREATE TABLE role
(
    id serial NOT NULL,
    code character(16)  NOT NULL,
    name character(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `user`
(
    id serial NOT NULL,
    role_id integer NOT NULL
        CONSTRAINT fk_user_role
        REFERENCES role (id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    login character(128) NOT NULL,
    password character(128) NOT NULL,
    name character(128) NOT NULL,
    rating numeric(5, 4),
    PRIMARY KEY (id)
);

CREATE TABLE service
(
    id bigserial NOT NULL,
    parent_id integer,
    name character(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE profile
(
    id serial NOT NULL,
    user_id integer NOT NULL
        CONSTRAINT fk_profile_user
        REFERENCES user (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    service_id integer NOT NULL
        CONSTRAINT fk_profile_service
        REFERENCES service (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    PRIMARY KEY (id)
);

CREATE TABLE credential
(
    id serial NOT NULL,
    user_id integer NOT NULL
        CONSTRAINT fk_credential_user
        REFERENCES user (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    code character(16) NOT NULL,
    value character(128),
    name character(128),
    PRIMARY KEY (id),
    UNIQUE (user_id, code)
);

CREATE TABLE offer
(
    id serial NOT NULL,
    title character(128) NOT NULL,
    description character(256),
    user_id integer NOT NULL
        CONSTRAINT fk_offer_user
        REFERENCES user (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    service_id integer NOT NULL
        CONSTRAINT fk_offer_service
        REFERENCES service (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    PRIMARY KEY (id)
);

