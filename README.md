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

insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test01', 25, 'MALE', '테스트01', 'ACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test02', 31, 'FEMALE', '테스트02', 'INACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test03', 33, 'FEMALE', '테스트03', 'ACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test04', 27, 'FEMALE', '테스트04', 'ACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test05', 21, 'MALE', '테스트05', 'ACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test06', 38, 'MALE', '테스트06', 'INACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test07', 45, 'FEMALE', '테스트07', 'ACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test08', 42, 'MALE', '테스트08', 'ACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test09', 34, 'MALE', '테스트09', 'ACTIVE');
insert into USER (USER_ID, USER_AGE, USER_GENDER, USER_NAME, USER_STATUS) values ('test10', 29, 'FEMALE', '테스트10', 'BLOCK');
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