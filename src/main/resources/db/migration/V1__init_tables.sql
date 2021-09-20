CREATE TABLE `user`
(
    id bigserial NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE service
(
    id bigserial NOT NULL,
    parent_id integer,
    name character(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE offer
(
    id bigserial NOT NULL,
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
