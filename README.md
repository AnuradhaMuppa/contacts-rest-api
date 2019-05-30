# contacts-rest-api
contacts-rest-api project


# Description :
  This poc demonstrates use of spring boot jpa by developoing a contacts resful api.
  
# Technologies Used
 ## Java 1.8
 ## Maven 3
 ## Spring boot starter
## H2  database
 ## spring jpa

# SQL queries to create tables in DB

CREATE TABLE CONTACT(ID INT NOT NULL PRIMARY KEY, FIRST_NAME VARCHAR(15), MIDDLE_NAME VARCHAR(15), LAST_NAME VARCHAR(15), PHONE_NUMBER NUMBER(15), PHONE_TYPE VARCHAR(10), EMAIL VARCHAR(50),
STREET VARCHAR(100), CITY VARCHAR(100), STATE VARCHAR(100), ZIP NUMBER(11));

CREATE TABLE PHONE(ID INT NOT NULL PRIMARY KEY, FK_CONTACT_ID INT references CONTACT(ID), PHONE_NUMBER NUMBER(12), TYPE VARCHAR(10));
