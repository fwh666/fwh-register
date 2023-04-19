package club.fuwenhao.controller;

import club.fuwenhao.bean.User;
import club.fuwenhao.enums.ResponseCodeEnum;
import club.fuwenhao.result.RespEntity;
import club.fuwenhao.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping("/auth")
    @CrossOrigin
    public RespEntity<String> auth(@RequestHeader("Authorization") String token, HttpServletResponse response) throws IOException {
        return RespEntity.success(userService.auth(token, response));
    }

    @PostMapping("/register")
    public RespEntity<User> register(@RequestBody User user) {
        return RespEntity.success(userService.register(user));
    }

    @PostMapping("/login")
    public RespEntity<String> login(@RequestBody User user, HttpServletResponse response) {
        try {
            String login = userService.login(user.getUsername(), user.getPassword());
            return RespEntity.success(login);
        } catch (Exception e) {
            return RespEntity.failure(ResponseCodeEnum.LOGIN_FAIL, e.getMessage());
        }
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