package com.dc.hexagonal.hexagonalarchitecture.account.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.hexagonal.hexagonalarchitecture.account.adapter.out.persistence.model.AccountJpaEntity;

public interface AccountRepository extends JpaRepository<AccountJpaEntity, Long> {

}
