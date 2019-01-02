package com.rest.ws.primaryApp.controller;

import com.rest.ws.primaryApp.model.dto.UserDto;
import com.rest.ws.primaryApp.model.requests.UserDetailsRequestModel;
import com.rest.ws.primaryApp.model.responses.UserRest;
import com.rest.ws.primaryApp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable("id") String id) {

        UserRest returnValue = new UserRest();
        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnValue);
        return returnValue;
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel) {

        UserRest returnValue = new UserRest();
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