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

USER_ORDER {
    bigint id PK "NOT NULL AUTO_INCREMENT"
    bigint user_id "NOT NULL"
    varchar(50) status "NOT NULL"
    decimal amount "decimal(11,4) NOT NULL"
    datetime ordered_at
    datetime accepted_at
    datetime cooking_started_at
    datetime delivery_started_at
    datetime received_at
  }

  USER_ORDER_MENU {
    bigint id PK "NOT NULL AUTO_INCREMENT"
    bigint user_order_id "NOT NULL"
    bigint store_menu_id "NOT NULL"
    varchar(50) status "NOT NULL"
  }

%% Relationships
  STORE ||--o{ STORE_MENU : "has many"
  USER ||--o{ USER_ORDER : "places"
  USER_ORDER ||--o{ USER_ORDER_MENU : "contains"
  STORE_MENU ||--o{ USER_ORDER_MENU : "is part of"
