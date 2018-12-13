package com.rest.ws.primaryApp.service;

import com.rest.ws.primaryApp.model.dto.UserDto;
import com.rest.ws.primaryApp.model.entity.UserEntity;
import com.rest.ws.primaryApp.repository.UserRepository;
import com.rest.ws.primaryApp.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto user) {

        UserEntity storedDetails = userRepository.findUserByEmail(user.getEmail());
        if(storedDetails != null) throw new RuntimeException("Record already exist");

        String publicUserId = utils.generateUserId(15);

        UserEntity userEntity =  new UserEntity();
        BeanUtils.copyProperties(user, userEntity);


        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(publicUserId);

        UserEntity storedUserDetails = userRepository.save(userEntity);



        UserDto returnValue =  new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;

    }
}
