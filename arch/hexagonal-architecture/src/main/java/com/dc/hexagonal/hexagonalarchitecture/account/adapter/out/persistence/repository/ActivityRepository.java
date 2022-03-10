package com.dc.hexagonal.hexagonalarchitecture.account.adapter.out.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dc.hexagonal.hexagonalarchitecture.account.adapter.out.persistence.model.ActivityJpaEntity;

/**
 * we use Spring Data to create repository interfaces that provide basic CRUD
 * functionality out of the box as well as custom queries to load certain
 * activities from the database
 * 
 * @author wlopez
 *
 */
public interface ActivityRepository extends JpaRepository<ActivityJpaEntity, Long> {

	@Query("select a from ActivityJpaEntity a where a.ownerAccountId = :ownerAccountId " + "and a.timestamp >= :since")
	List<ActivityJpaEntity> findByOwnerSince(@Param("ownerAccountId") Long ownerAccountId,
			@Param("since") LocalDateTime since);

	@Query("select sum(a.amount) from ActivityJpaEntity a " + "where a.targetAccountId = :accountId "
			+ "and a.ownerAccountId = :accountId " + "and a.timestamp < :until")
	Long getDepositBalanceUntil(@Param("accountId") Long accountId, @Param("until") LocalDateTime until);

	@Query("select sum(a.amount) from ActivityJpaEntity a " + "where a.sourceAccountId = :accountId "
			+ "and a.ownerAccountId = :accountId " + "and a.timestamp < :until")
	Long getWithdrawalBalanceUntil(@Param("accountId") Long accountId, @Param("until") LocalDateTime until);

}
