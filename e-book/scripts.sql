-- PUBLIC.BOOK definition

-- Drop table

 DROP TABLE PUBLIC.BOOK;
 --DROP INDEX PRIMARY_KEY;


CREATE TABLE PUBLIC.BOOK (
	ID INT NOT NULL IDENTITY,
	AUTHOR VARCHAR(255) NOT NULL,
	BACK_COVER_IMAGE_URL VARCHAR(255),
	DESCRIPTION VARCHAR(1000) NOT NULL,
	FRONT_COVER_IMAGE_URL VARCHAR(255),
	ISBN VARCHAR(255),
	PRICE DOUBLE NOT NULL,
	TITLE VARCHAR(255) NOT NULL,
	CONSTRAINT CONSTRAINT_1 PRIMARY KEY (ID)
);
CREATE UNIQUE INDEX PRIMARY_KEY ON PUBLIC.BOOK (ID);


INSERT INTO PUBLIC.BOOK
(TITLE, AUTHOR, DESCRIPTION, ISBN, PRICE, FRONT_COVER_IMAGE_URL, BACK_COVER_IMAGE_URL)
VALUES('The 7 Habits of Highly Effective People', 'Stephen R. Covey',
'This beloved classic presents a principle-centered approach for solving both personal and professional problems. With penetrating insights and practical anecdotes',
'978-1982137274', 
12.26, 'images/978-1982137274_front_cover.jpg', 'images/978-1982137274_rear_cover.jpg');


INSERT INTO PUBLIC.BOOK
(TITLE, AUTHOR, DESCRIPTION, ISBN, PRICE, FRONT_COVER_IMAGE_URL, BACK_COVER_IMAGE_URL)
VALUES('Can\''t Hurt Me: Master Your Mind and Defy the Odds', 'David Goggins',
'For David Goggins, childhood was a nightmare--poverty, prejudice, and physical abuse colored his days and haunted his nights. But through self-discipline, mental toughness, and hard work, Goggins transformed himself from a depressed, overweight young man with no future into a US Armed Forces icon and one of the world\''s top endurance athletes','978-1544512280', 
22.49, 'images/978-1544512280_front_cover.jpg', 'images/978-1544512280_rear_cover.jpg');


INSERT INTO PUBLIC.BOOK
(TITLE, AUTHOR, DESCRIPTION, ISBN, PRICE, FRONT_COVER_IMAGE_URL, BACK_COVER_IMAGE_URL)
VALUES('The 5 Years Before You Retire', 'Emily Guy Birken',
'Retirement Planning When You Need It the Most','978-1507213605', 
10.39, 'images/978-1507213605_front_cover.jpg', 'images/978-1507213605_rear_cover.jpg');



INSERT INTO PUBLIC.BOOK
(TITLE, AUTHOR, DESCRIPTION, ISBN, PRICE, FRONT_COVER_IMAGE_URL, BACK_COVER_IMAGE_URL)
VALUES('Can/''t Hurt Me: Master Your Mind and Defy the Odds', 'David Goggins',
'Author David Goggins, childhood was a nightmare -- poverty, prejudice, and physical abuse colored his days and haunted his nights. But through self-discipline, mental toughness, and hard work, Goggins transformed himself from a depressed, overweight young man with no future into a U.S. Armed Forces icon and one of the world/''s top endurance athletes. The only man in history to complete elite training as a Navy SEAL, Army Ranger, and Air Force Tactical Air Controller, he went on to set records in numerous endurance events, inspiring Outside magazine to name him The Fittest (Real) Man in America.'
,'978-1544512273', 
17.99, 'images/978-1544512273_front_cover.jpg', 'images/978-1544512273_rear_cover.jpg');


INSERT INTO PUBLIC.BOOK
(TITLE, AUTHOR, DESCRIPTION, ISBN, PRICE, FRONT_COVER_IMAGE_URL, BACK_COVER_IMAGE_URL)
VALUES('Never Finished: Unshackle Your Mind and Win the War Within', 'David Goggins',
'The stories and lessons in this raw, revealing, unflinching memoir offer the reader a blueprint they can use to climb from the bottom of the barrel into a whole new stratosphere that once seemed unattainable. Whether you feel off-course in life, are looking to maximize your potential or drain your soul to break through your so-called glass ceiling, this is the only book you will ever need.'
,'978-1544534077', 
19.79, 'images/978-1544534077_front_cover.jpg', 'images/978-1544534077_rear_cover.jpg');

