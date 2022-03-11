package com.dc.hexagonal.hexagonalarchitecture.account.adapter.out.persistence.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityJpaEntity {

	@Id
	@GeneratedValue
	private Long id;

	private LocalDateTime timestamp;

	@Column(name = "owner_account_id")
	private Long ownerAccountId;

	@Column(name = "source_account_id")
	private Long sourceAccountId;

	@Column(name = "target_account_id")
	private Long targetAccountId;

	@Column
	private Long amount;

}
