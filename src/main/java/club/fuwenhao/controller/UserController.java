package club.fuwenhao.controller;

import club.fuwenhao.bean.User;
import club.fuwenhao.result.RespEntity;
import club.fuwenhao.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public RespEntity<User> register(@RequestBody User user) {
        return RespEntity.success(userService.register(user));
    }

    @PostMapping("/login")
    public RespEntity<String> login(@RequestBody User user) {
        return RespEntity.success(userService.login(user.getUsername(), user.getPassword()));
    }

    @GetMapping("/me")
    public RespEntity<User> me(@RequestHeader("Authorization") String token) {
        if (token.contains("Bearer")) {
            return RespEntity.success(userService.getUserByToken(token.substring(7)));
        } else {
            return RespEntity.success(userService.getUserByToken(token));
        }
    }
}