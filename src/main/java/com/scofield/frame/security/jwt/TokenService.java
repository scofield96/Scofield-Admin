package com.scofield.frame.security.jwt;

import com.scofield.frame.constant.Constants;
import com.scofield.frame.utils.AddressUtils;
import com.scofield.frame.utils.RedisUtils;
import com.scofield.frame.utils.uuid.IdUtils;
import com.scofield.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Scofield
 * @description:
 * @date: 2021/4/25
 * @email: 543196660@qq.com
 * @time: 13:34
 */
@Service
public class TokenService {
    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;
    @Autowired
    UserService userService;
    protected static final long MILLIS_SECOND = 1000L;  //1s

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND; //1m

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L; // 20m

    @Autowired
    RedisUtils redisUtils;


    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public JwtUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            Claims claims = parseToken(token);
            // 解析对应的权限以及用户信息
            String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
            String userKey = getTokenKey(uuid);
            JwtUser user = redisUtils.getCacheObject(userKey);
            return user;
        }
        return null;
    }


    /**
     * 创建令牌
     *
     * @param jwtUser 用户信息
     * @return 令牌
     */
    public String createToken(JwtUser jwtUser) {
        String token = IdUtils.fastUUID();
        jwtUser.setToken(token);
        setUserAgent(jwtUser); //代理信息
        refreshToken(jwtUser); //刷新令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param jwtUser
     * @return 令牌
     */
    public void verifyToken(JwtUser jwtUser) {
        long expireTime = jwtUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(jwtUser);
        }
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims) {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 设置用户代理信息
     *
     * @param jwtUser 登录信息
     */
    private void setUserAgent(JwtUser jwtUser) {
        String hostIp = AddressUtils.getHostIp();
        String address = AddressUtils.getAddress(hostIp);
        jwtUser.setIpaddr(hostIp);
        jwtUser.setLoginLocation(address);
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            redisUtils.deleteObject(userKey);
        }
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(JwtUser jwtUser) {
        if ((jwtUser != null && StringUtils.isNotEmpty(jwtUser.getToken()))) {
            refreshToken(jwtUser);
        }
    }

    private void refreshToken(JwtUser jwtUser) {
        jwtUser.setLoginTime(System.currentTimeMillis());
        jwtUser.setExpireTime(jwtUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        //根据uuid将jwtUser缓存
        String userKey = getTokenKey(jwtUser.getToken());
        redisUtils.set(userKey, jwtUser, expireTime, TimeUnit.MINUTES);
    }

    private String getTokenKey(String uuid) {
        return Constants.LOGIN_TOKEN_KEY + uuid;
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }


}
