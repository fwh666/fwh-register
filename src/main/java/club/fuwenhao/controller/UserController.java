package club.fuwenhao.controller;

import club.fuwenhao.bean.User;
import club.fuwenhao.enums.ResponseCodeEnum;
import club.fuwenhao.result.RespEntity;
import club.fuwenhao.service.impl.UserService;
import club.fuwenhao.util.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/checkCurrentAuth")
    public RespEntity<String> auth(@RequestHeader("Authorization") String token, HttpServletRequest request) {
        if (StringUtils.isEmpty(token)||"null".equals(token)) {
            String realIp = RequestUtil.getRealIp(request);
            String count = stringRedisTemplate.opsForValue().get(realIp);
            if (Integer.valueOf(count) > 5) {
                return RespEntity.failure(ResponseCodeEnum.INVALID_TOKEN, "参数无效");
            } else {
                return RespEntity.success("访客五次限制");
            }
        }
        return RespEntity.success(userService.auth(token));
    }

    @PostMapping("/register")
    public RespEntity<User> register(@RequestBody User user) {
        User register = userService.register(user);
        return RespEntity.success(new User().setUsername(register.getUsername()));
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