package com.rest.ws.primaryApp.repository;

import com.rest.ws.primaryApp.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    public UserEntity findUserByEmail(String email);

    public UserEntity findUserByUserId(String userId);
}
