package rest.controllers;

import org.springframework.web.bind.annotation.*;
import typingworld.model.User;
import typingworld.services.UserService;

import java.util.List;

@RestController
public class UserController {

    @PostMapping("/register")
    public boolean register(final String username,
                            final String password) {
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

    @RequestMapping("/users")
    public List<User> getUsers(){
        return UserService.getUsers();
    }

}
