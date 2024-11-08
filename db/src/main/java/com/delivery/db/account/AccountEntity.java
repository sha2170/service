package com.delivery.db.account;

import com.delivery.db.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true) // 객체 비교 (부모에 있는 값까지 포함해서 비교)
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {
}