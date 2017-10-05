
DROP TABLE recordingAlbums;
DROP TABLE recordingGroup;
DROP TABLE recordingStudios;
CREATE TABLE   recordingGroup
(    
    group_name    VARCHAR(60)     NOT NULL,
    lead_singer   VARCHAR(60)     NOT NULL,
    year_formed   CHAR(4)                 ,
    genre         VARCHAR(60)     NOT NULL,
    CONSTRAINT pk_recGroup PRIMARY KEY (group_name));
CREATE TABLE   recordingStudios
(    
    studio_name    VARCHAR(60)     NOT NULL,    
    studio_address VARCHAR(60)     NOT NULL,
    studio_owner   VARCHAR(60)     NOT NULL,
    studio_phone   VARCHAR(15)     NOT NULL,
    CONSTRAINT pk_studios PRIMARY KEY (studio_name));
CREATE TABLE   recordingAlbums
(
    album_title  VARCHAR(60)     NOT NULL,
    group_name   VARCHAR(60)     REFERENCES recordingGroup (group_name),
    studio_name  VARCHAR(60)     REFERENCES recordingStudios (studio_name),
    date_recorded VARCHAR(60)    NOT NULL,
    album_length  VARCHAR(60)   NOT NULL,
    number_of_songs CHAR(60)     NOT NULL,
    CONSTRAINT fk_recordingAlbums_group FOREIGN KEY (group_name) REFERENCES recordingGroup (group_name),
    CONSTRAINT fk_recordingAlbums_studios FOREIGN KEY (studio_name) REFERENCES recordingStudios (studio_name),
    CONSTRAINT pk_recordingAlbums PRIMARY KEY (album_title, group_name));

INSERT INTO recordingGroup (group_Name, lead_singer, year_formed, genre)
    VALUES ('Green Day', 'Billie Joe Armstrong', '1986', 'Punk/Rock');
INSERT INTO recordingGroup (group_Name, lead_singer, year_formed, genre)
    VALUES ('Linkin Park', 'Chester Bennington', '1996', 'Alternative Rock');
INSERT INTO recordingGroup (group_Name, lead_singer, year_formed, genre)
    VALUES ('Coldplay', 'Chris Martin', '1996', 'Alternative Rock');
INSERT INTO recordingGroup (group_Name, lead_singer, year_formed, genre)
    VALUES ('Red Hot Chili Peppers', 'Anthony Kiedis', '1983', 'Alternative Rock');

INSERT INTO recordingStudios (studio_name, studio_address, studio_owner, studio_phone)
    VALUES ('Ocean Way Recording', '6050 Sunset Blvd, Los Angeles, CA 90028', 'Allen Sides', '(323) 467-9375');
INSERT INTO recordingStudios (studio_name, studio_address, studio_owner, studio_phone)
    VALUES ('NRG Recording Studios', '11128 Weddington St, North Hollywood, CA 91601', 'Jay Baumgardner', '(818) 760-7841');
INSERT INTO recordingStudios (studio_name, studio_address, studio_owner, studio_phone)
    VALUES ('Magic Shop Inc', '49 Crosby St #1, New York, NY 10012', 'Steve Rosenthal', '(212) 226-7035');
INSERT INTO recordingStudios (studio_name, studio_address, studio_owner, studio_phone)
    VALUES ('Cello Studios', '6000 Sunset Blvd, Los Angeles, CA 90028', 'Candace Stewart', '(323)957-6969');

INSERT INTO recordingAlbums (album_title, group_name, studio_name, date_recorded, album_length, number_of_songs)
    VALUES ('American Idiot', 'Green Day', 'Ocean Way Recording', 'April 18,2003 - March 26, 2004', '57:12', '13');
INSERT INTO recordingAlbums (album_title, group_name, studio_name, date_recorded, album_length, number_of_songs)
    VALUES ('Dookie', 'Green Day', 'Ocean Way Recording', 'September - October 1993', '39:38', '14');
INSERT INTO recordingAlbums (album_title, group_name, studio_name, date_recorded, album_length, number_of_songs)
    VALUES ('21st Century Breakdown', 'Green Day', 'Ocean Way Recording', 'January 2008 - April 2009', '69:16', '18');

INSERT INTO recordingAlbums (album_title, group_name, studio_name, date_recorded, album_length, number_of_songs)
    VALUES ('Meteora', 'Linkin Park', 'NRG Recording Studios', 'April - December 2002', '36:43', '13');
INSERT INTO recordingAlbums (album_title, group_name, studio_name, date_recorded, album_length, number_of_songs)
    VALUES ('Hybrid Theory', 'Linkin Park', 'NRG Recording Studios', 'March - July 2000', '37:45', '12');
INSERT INTO recordingAlbums (album_title, group_name, studio_name, date_recorded, album_length, number_of_songs)
    VALUES ('Minutes to Midnight', 'Linkin Park', 'NRG Recording Studios', 'January 2006 - February 2007', '43:23', '12');

INSERT INTO recordingAlbums (album_title, group_name, studio_name, date_recorded, album_length, number_of_songs)
    VALUES ('Viva la Vida or Death and All His Friends', 'Coldplay', 'Magic Shop Inc', 'November 2006 - April 2008', '45:49', '13');

INSERT INTO recordingAlbums (album_title, group_name, studio_name, date_recorded, album_length, number_of_songs)
    VALUES ('By the Way', 'Red Hot Chili Peppers', 'Cello Studios', 'November 2001 - May 2002', '68:46', '16');

INSERT INTO recordingAlbums (album_title, group_name, studio_name, date_recorded, album_length, number_of_songs)
    VALUES ('Californication', 'Red Hot Chili Peppers', 'Cello Studios', 'December 1998 - March 1999', '56:24', '15');

select * from recordingGroup;
select * from recordingStudios;
select * from recordingAlbums;