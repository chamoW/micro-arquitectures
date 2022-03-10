package com.dc.clean.user.adapter.out.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dc.clean.user.adapter.out.persistence.model.UserDataMapper;


@Repository
public interface SpringJpaUserRepository extends CrudRepository<UserDataMapper, String> {
}
