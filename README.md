---
delivery DB : ERD
---
erDiagram
  USER{
    bigint id PK "NOT NULL AUTO_INCREMENT"
    varchar(50) name "NOT NULL"
    varchar(100) email "NOT NULL"
    varchar(100) password "NOT NULL"
    varchar(50) status "NOT NULL"
    varchar(150) address "NOT NULL"
    datetime registered_at
    datetime unregistered_at
    datetime last_login_at
  }


## delivery DB의 User 테이블
```
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `status` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `address` varchar(150) COLLATE utf8mb4_bin NOT NULL,
  `registered_at` datetime DEFAULT NULL,
  `unregistered_at` datetime DEFAULT NULL,
  `last_login_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin
```
