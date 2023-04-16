package club.fuwenhao.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V4.4
 * @Title: jwt工具
 * @ClassName: com.jdcity.joycity.utils.JwtUtil.java
 * @Description:
 * @author: fuwenhao
 * @date: 2022-06-22 11:28
 */
@Slf4j
public class JwtUtil {
    public static String SECRET = "authcenter";
    /**
     * 三小时失效时间
     */
    private static final long EXPIRATION = System.currentTimeMillis() + 3 * 60 * 60 * 1000; // three hours from now


    public static <T> String generateToken(T t) {
        Date expireDate = new Date(EXPIRATION);
        Date now = new Date();
        Map<String, Object> map = new HashMap<>(2);
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        JWTCreator.Builder token = JWT.create()
                .withHeader(map)
                .withExpiresAt(expireDate)
                .withIssuedAt(now)
                .withNotBefore(now);

        token.withClaim("data", JSON.toJSONString(t));
        return token.sign(Algorithm.HMAC256(SECRET));
    }

    public static <T> T parseToken(String token, Class<T> aclass) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            String string = claims.get("data").asString();
            return JSON.parseObject(string, aclass);
        } catch (Exception e) {
            log.error("JWT解析异常,Token为:{}", token, e);
        }
        return null;
    }

    /**
     * @Title: 解析时间戳
     * @ClassName: club.fuwenhao.util.JwtUtil.java
     * @Description:
     * @author: fuwenhao
     * @date: 2023-04-16 1:06 PM
     * @version V4.5.3
     */
    public static long parseDateLong(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            Date expiresAt = jwt.getExpiresAt();
            return expiresAt.getTime();
        } catch (Exception e) {
            log.error("JWT解析异常,Token为:{}", token, e);
        }
        return -1L;
    }


    public static void main(String[] args) {
        String zhang = generateToken("zhang");
        System.out.println(zhang);
        long l = parseDateLong(zhang);
        System.out.println(l);


    }
//    public static void main(String[] args) {
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE2NjkwMDA2MjQsImRhdGEiOiJ7XCJkYXRhU291cmNlXCI6XCJQUk9WSURFUlwiLFwiaWRcIjoyOSxcImxvZ2luTmFtZVwiOlwiZnV3ZW5oYW9cIixcIm1vYmlsZVwiOlwiMTc2MjMzMzg3NjZcIixcIm9wZXJhdG9yXCI6XCJUZXN0YWRtaW5cIixcInBhc3N3b3JkXCI6XCIzMDEyYjY5NTEzMTgyNzgyN2I4OWM3MTUzOGNkNWRmMFwiLFwicHJvdmlkZXJDb2RlXCI6XCJLRVBMRVJcIixcInJvbGVJZFwiOjEsXCJzdGF0dXNcIjowLFwidXNlck5hbWVcIjpcImZ1d2VuaGFvXCJ9IiwiZXhwIjoxNjk3ODAwNjI0LCJpYXQiOjE2NjkwMDA2MjR9.11B3EMdNtoMYfhQrK5HNNBcBecQoHscpliGAj6eB2uU";
//        JoycityAccount joycityAccount1 = parseToken(token, JoycityAccount.class);
//        System.out.println(joycityAccount1);
//    }
}
