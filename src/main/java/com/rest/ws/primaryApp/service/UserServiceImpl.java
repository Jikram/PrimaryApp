package com.rest.ws.primaryApp.service;


import com.rest.ws.primaryApp.exception.UserServiceException;
import com.rest.ws.primaryApp.model.dto.UserDto;
import com.rest.ws.primaryApp.model.entity.UserEntity;
import com.rest.ws.primaryApp.model.responses.ErrorMessage;
import com.rest.ws.primaryApp.model.responses.ErrorMessages;
import com.rest.ws.primaryApp.repository.UserRepository;
import com.rest.ws.primaryApp.utils.Utils;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

        //This is to prevent duplicate records.
        UserEntity storedDetails = userRepository.findUserByEmail(user.getEmail());
        if (storedDetails != null) throw new RuntimeException("Record already exist");

        // This is generating the public user id.
        String publicUserId = utils.generateUserId(15);


        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        //This is for encrypting the password.
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userEntity.setUserId(publicUserId);

        UserEntity storedUserDetails = userRepository.save(userEntity);


        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;

    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity == null)
            throw new UsernameNotFoundException(email);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity,returnValue);

        return returnValue;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserDto returnValue = new UserDto();
        UserEntity userEntity = userRepository.findUserByUserId(userId);
        if(userEntity == null) throw new UsernameNotFoundException(userId);
        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        UserDto returnValue = new UserDto();
        UserEntity userEntity = userRepository.findUserByUserId(userId);
        if (userEntity == null)
            throw new UserServiceException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        UserEntity updatedUserDetails = userRepository.save(userEntity);
        BeanUtils.copyProperties(updatedUserDetails,returnValue);
        return returnValue;
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findUserByUserId(userId);
        if (userEntity == null)
            throw new UserServiceException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
        userRepository.delete(userEntity);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity == null)
            throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());


    }
}
