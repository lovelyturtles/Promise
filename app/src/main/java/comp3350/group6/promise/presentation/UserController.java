package comp3350.group6.promise.presentation;

import comp3350.group6.promise.business.UserService;
import lombok.Data;


@Data
public class UserController {


    private UserService userService;

    public void say(){
        userService.sayHello();
    }
}
