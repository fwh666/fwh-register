package club.fuwenhao.controller;

import club.fuwenhao.bean.User;
import club.fuwenhao.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    @GetMapping("/me")
    public User me(@RequestHeader("Authorization") String token) {
        if (token.contains("Bearer")) {
            return userService.getUserByToken(token.substring(7));
        } else {
            return userService.getUserByToken(token);
        }
    }
}