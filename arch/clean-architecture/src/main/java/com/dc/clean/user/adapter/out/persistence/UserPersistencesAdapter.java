package com.dc.clean.user.adapter.out.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import com.dc.clean.user.adapter.out.persistence.model.UserDataMapper;
import com.dc.clean.user.usercase.dto.UserDsRequestModel;
import com.dc.clean.user.usercase.port.out.UserRegisterDsGateway;

/**
 * UserPersistencesAdapter is the outcomming adapter that implementes the outcomming port UserRegisterDsGateway
 * and uses the SpringJpaUserRepository
 */
public class UserPersistencesAdapter implements UserRegisterDsGateway {

    @Autowired
    private SpringJpaUserRepository repository;


    @Override
    public boolean existsByName(String name) {
        return repository.existsById(name);
    }

    @Override
    public void save(UserDsRequestModel requestModel) {
        UserDataMapper user = new UserDataMapper(requestModel.getName(), requestModel.getPassword(), requestModel.getCreationTime());

        repository.save(user);

    }
}
