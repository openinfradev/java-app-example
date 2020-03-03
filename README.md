## Introduction

> Sample Java Web Application with Redis, MariaDB.

## Database 및 계정 생성
```
create database sample default character set utf8 collate utf8_unicode_ci;
create user 'test'@'%' identified by 'test00';
grant all privileges on sample.* to 'test'@'%' with grant option;
```


## 테이블 생성 및 초기 샘플 데이터
```
CREATE TABLE USER
(
    USER_ID VARCHAR(100) PRIMARY KEY NOT NULL,
    USER_AGE INT(11) DEFAULT 'NULL',
    USER_GENDER VARCHAR(20) DEFAULT 'NULL',
    USER_NAME VARCHAR(50) DEFAULT 'NULL',
    USER_STATUS VARCHAR(20) DEFAULT 'NULL'
);

insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test01', 25, 'MALE', 'Tester 1', 'ACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test02', 31, 'FEMALE', 'Tester 2', 'INACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test03', 33, 'FEMALE', 'Tester 3', 'ACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test04', 27, 'FEMALE', 'Tester 4', 'ACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test05', 21, 'MALE', 'Tester 5', 'ACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test06', 38, 'MALE', 'Tester 6', 'INACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test07', 45, 'FEMALE', 'Tester 7', 'ACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test08', 42, 'MALE', 'Tester 8', 'ACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test09', 34, 'MALE', 'Tester 9', 'ACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test10', 29, 'FEMALE', 'Tester 10', 'BLOCK');
```

## Docker Build
```
docker build --network=host -t java-sample:latest .
```

## Docker Run
```
docker run \
-d --name sample-app \
-p 38080:8080 \
-e DB_HOST=exntu.kr \
-e DB_PORT=33306 \
-e REDIS_HOST=exntu.kr \
-e REDIS_PORT=26379 \
java-sample:latest 
```