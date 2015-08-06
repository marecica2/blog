CREATE TABLE BLOGENTRY (ID BIGINT NOT NULL, AUTHOR VARCHAR(255), CONTENT CLOB(2147483647), CREATED TIMESTAMP, TITLE VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE BOOK (ID BIGINT NOT NULL, DESCRIPTION VARCHAR(2000), NUMBER VARCHAR(255), PRICE FLOAT, TITLE VARCHAR(255) NOT NULL, PRIMARY KEY (ID))
CREATE TABLE CUSTOMER (ID BIGINT NOT NULL, DATEOFBIRTH TIMESTAMP, EMAIL VARCHAR(255), FIRSTNAME VARCHAR(255), LASTNAME VARCHAR(255), PHONENUMBER VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(15), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
