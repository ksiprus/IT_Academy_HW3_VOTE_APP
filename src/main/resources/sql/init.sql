CREATE TABLE IF NOT EXISTS vote_app.vote
(
    dt_create timestamp without time zone NOT NULL,
    artist    character varying           NOT NULL,
    genres    character varying[]         NOT NULL,
    about     text                        NOT NULL
);

SELECT dt_create, artist, genres, about FROM vote_app.vote;


TRUNCATE TABLE vote_app.vote;
