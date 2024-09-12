CREATE TABLE competition (
	id BIGINT PRIMARY KEY NOT NULL,
	name VARCHAR(128) NULL,
	code VARCHAR(32) NULL,
	areaName VARCHAR(32) NULL
);

CREATE TABLE team (
	id BIGINT PRIMARY KEY NOT NULL,
	name VARCHAR(128) NULL,
	tla VARCHAR(32) NULL,
    shortName VARCHAR(32) NULL,
    areaName VARCHAR(32) NULL,
    address VARCHAR(256) NULL,
    competitionId BIGINT NULL,
    FOREIGN KEY (competitionId) REFERENCES competition(id)
);

CREATE TABLE player (
	id BIGINT PRIMARY KEY NOT NULL,
	name VARCHAR(128) NULL,
	position VARCHAR(32) NULL,
    dateOfBirth VARCHAR(32) NULL,
    nationality VARCHAR(32) NULL,
    teamId BIGINT NULL,
    FOREIGN KEY (teamId) REFERENCES team(id)
);

CREATE TABLE coach (
	id BIGINT PRIMARY KEY NOT NULL,
	name VARCHAR(128) NULL,
    dateOfBirth VARCHAR(32) NULL,
    nationality VARCHAR(32) NULL,
    teamId BIGINT NULL,
    FOREIGN KEY (teamId) REFERENCES team(id)
);