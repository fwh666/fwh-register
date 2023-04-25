package club.fuwenhao.service.impl;

import club.fuwenhao.bean.User;
import club.fuwenhao.exception.AppBusinessCode;
import club.fuwenhao.exception.AppBusinessException;
import club.fuwenhao.service.UserRepository;
import club.fuwenhao.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //    @Autowired(required = false)
//    @Nullable
    @Resource
    private PasswordEncoder passwordEncoder;


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public User register(User user) {
        User byUsername = userRepository.findByUsername(user.getUsername());
        if (ObjectUtils.isEmpty(byUsername)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreatedTime(new Date());
            return userRepository.save(user);
        } else {
            throw new AppBusinessException(AppBusinessCode.USER_EXIST);
        }
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        String token = JwtUtil.generateToken(user);
//        redisTemplate.opsForValue().set(token, user.getId(), JwtUtil.parseDateLong(token) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        redisTemplate.opsForValue().set(token, user.getId(), 3, TimeUnit.HOURS);
        return token;
    }

    public User getUserByToken(String token) {
        Integer userId = (Integer) redisTemplate.opsForValue().get(token);
        if (userId == null) {
            throw new RuntimeException("Invalid token");
        }
        return userRepository.findById(Long.valueOf(userId)).orElse(null);
    }

    public String auth(String token) {
        Object o = redisTemplate.opsForValue().get(token);
        if (ObjectUtils.isEmpty(o)) {
            throw new AppBusinessException(AppBusinessCode.INVALID_TOKEN);
        }
        return o.toString();
    }
}