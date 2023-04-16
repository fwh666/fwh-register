package club.fuwenhao.controller;

import club.fuwenhao.bean.User;
import club.fuwenhao.enums.ResponseCodeEnum;
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
        try {
            if (token.contains("Bearer")) {
                return RespEntity.success(userService.getUserByToken(token.substring(7)));
            } else {
                return RespEntity.success(userService.getUserByToken(token));
            }
        } catch (Exception e) {
            return RespEntity.failure(ResponseCodeEnum.INVALID_TOKEN.getCode(), ResponseCodeEnum.INVALID_TOKEN.getMessage(), "");
        }
    }
}