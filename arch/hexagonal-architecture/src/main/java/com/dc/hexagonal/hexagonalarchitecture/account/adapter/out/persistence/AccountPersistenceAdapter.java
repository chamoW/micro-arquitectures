package com.dc.hexagonal.hexagonalarchitecture.account.adapter.out.persistence;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Component;

import com.dc.hexagonal.hexagonalarchitecture.account.adapter.out.persistence.mapper.AccountMapper;
import com.dc.hexagonal.hexagonalarchitecture.account.adapter.out.persistence.model.AccountJpaEntity;
import com.dc.hexagonal.hexagonalarchitecture.account.adapter.out.persistence.model.ActivityJpaEntity;
import com.dc.hexagonal.hexagonalarchitecture.account.adapter.out.persistence.repository.AccountRepository;
import com.dc.hexagonal.hexagonalarchitecture.account.adapter.out.persistence.repository.ActivityRepository;
import com.dc.hexagonal.hexagonalarchitecture.account.application.port.out.LoadAccountPort;
import com.dc.hexagonal.hexagonalarchitecture.account.application.port.out.UpdateAccountStatePort;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account.AccountId;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Activity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountStatePort {
	
	private final AccountRepository accountRepository;
	private final ActivityRepository activityRepository;
	private final AccountMapper accountMapper;

	
	@Override
	public Account loadAccount(AccountId accountId, LocalDateTime baselineDate) {
		
		
		AccountJpaEntity account = accountRepository.findById(accountId.getValue()).orElseThrow(EntityNotFoundException::new);
		
		List<ActivityJpaEntity> activities = activityRepository.findByOwnerSince(accountId.getValue(), baselineDate);
		
		Long withdrawalBalance = orZero(activityRepository.getWithdrawalBalanceUntil(accountId.getValue(), baselineDate));
		
		Long depositBalance = orZero(activityRepository.getDepositBalanceUntil(accountId.getValue(), baselineDate));
		
		return accountMapper.mapToDomainEntity(account, activities, withdrawalBalance, depositBalance);
		
	}

	
	@Override
	public void updateActivities(Account account) {
		
		for(Activity activity : account.getActivityWindow().getActivities()) {
			if(activity.getId() == null) {
				activityRepository.save(accountMapper.mapToJpaEntity(activity));
			}
		}
		

	}
	
	private Long orZero(Long value){
		return value == null ? 0L : value;
	}


}
