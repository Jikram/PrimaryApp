package com.rest.ws.primaryApp.model.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRest {

    public String userId;
    private String firstName;
    private String lastName;
    private String email;

}
