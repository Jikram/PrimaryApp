package com.rest.ws.primaryApp.controller;

import com.rest.ws.primaryApp.exception.UserServiceException;
import com.rest.ws.primaryApp.model.dto.UserDto;
import com.rest.ws.primaryApp.model.requests.UserDetailsRequestModel;
import com.rest.ws.primaryApp.model.responses.ErrorMessages;
import com.rest.ws.primaryApp.model.responses.UserRest;
import com.rest.ws.primaryApp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserRest getUser(@PathVariable("id") String id) {

        UserRest returnValue = new UserRest();
        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnValue);
        return returnValue;
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
    consumes = {MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE})
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel) throws Exception{

        UserRest returnValue = new UserRest();

        //if (userDetailsRequestModel.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        if (userDetailsRequestModel.getFirstName().isEmpty()) throw new NullPointerException("Object is null");
        UserDto userDto = new UserDto();


        BeanUtils.copyProperties(userDetailsRequestModel,userDto);

        UserDto createdUser = userService.createUser(userDto);

        BeanUtils.copyProperties(createdUser,returnValue);

        return returnValue;
    }
    @PutMapping
    public String updateUser() {

        return "update user in action";
    }

    @DeleteMapping
    public String deleteUser() {

        return "delete user in action";
    }
}