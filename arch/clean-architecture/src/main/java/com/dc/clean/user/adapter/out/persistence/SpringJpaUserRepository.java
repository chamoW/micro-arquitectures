package com.dc.clean.user.adapter.out.persistence;

import com.dc.clean.user.adapter.model.UserDataMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringJpaUserRepository extends CrudRepository<UserDataMapper, String> {
}
