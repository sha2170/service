## delivery DB의 user 테이블, store 테이블, store_menu 테이블
```mermaid
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

  STORE{
    bigint id PK "NOT NULL AUTO_INCREMENT"
    varchar(100) name "NOT NULL"
    varchar(150) address "NOT NULL"
    varchar(50) status "NOT NULL"
    varchar(50) category "NOT NULL"
    double star "default=0"
    varchar(200) thumbnail_url "NOT NULL"
    decimal minimum_amount "decimal(11,4) NOT NULL"
    decimal minimum_delivery_amount "decimal(11,4) NOT NULL"
    varchar(2) phone_number
  }

STORE_MENU{
  bigint id PK "NOT NULL AUTO_INCREMENT"
  bigint store_id "NOT NULL"
  varchar(100) name "NOT NULL"
  decimal amount "decimal(11,4) NOT NULL"
  varchar(50) status "NOT NULL"
  varchar(200) thumbnail_url "NOT NULL"
  int like_count  "DEFAULT 0"
  int sequence "DEFAULT 0"
}

STORE ||--o{ STORE_MENU : "has many"
```

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

```
CREATE TABLE IF NOT EXISTS `store` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `address` VARCHAR(150) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `category` VARCHAR(50) NOT NULL,
  `star` DOUBLE NULL DEFAULT 0,
  `thumbnail_url` VARCHAR(200) NOT NULL,
  `minimum_amount` DECIMAL(11,4) NOT NULL,
  `minimum_delivery_amount` DECIMAL(11,4) NOT NULL,
  `phone_number` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
```

```
CREATE TABLE IF NOT EXISTS `store_menu` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `store_id` BIGINT(32) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `amount` DECIMAL(11,4) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `thumbnail_url` VARCHAR(200) NOT NULL,
  `like_count` INT NULL DEFAULT 0,
  `sequence` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
```
