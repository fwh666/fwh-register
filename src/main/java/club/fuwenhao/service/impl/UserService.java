package club.fuwenhao.service.impl;

import club.fuwenhao.bean.User;
import club.fuwenhao.service.UserRepository;
import club.fuwenhao.util.JwtUtil;
import io.jsonwebtoken.JwtBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.util.annotation.Nullable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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

    public String auth(String token, HttpServletResponse response) throws IOException {
        Object o = redisTemplate.opsForValue().get(token);
        if (ObjectUtils.isEmpty(o)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect("/login");
        }
        return o.toString();
    }
}