package rest.controllers;

import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import typingworld.services.UserService;

@RestController
public class UserController {

    @RequestMapping("/register")
    public boolean register(@RequestParam(value = "username") final String username,
                            @RequestParam(value = "password") final String password) {
        if (username == null || password == null) {
            return false;
        }
        return UserService.register(username, password);
    }

    @RequestMapping("/login")
    public boolean login(@RequestParam(value = "username") final String username,
                         @RequestAttribute(value ="password") final String password) {
        if (username == null || password == null) {
            return false;
        }
        return false;
    }

    @RequestMapping("/logout")
    public boolean logout() {
        return false;
    }

}
