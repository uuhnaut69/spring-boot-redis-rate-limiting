package com.uuhnaut69.demo.config;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.lang.Boolean.FALSE;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class RateLimitingInterceptor implements HandlerInterceptor {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  @Autowired
  private RedisScript<Boolean> rateLimitingScript;

  private static final String API_KEY = "x-api-key";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    var apiKey = request.getHeader(API_KEY);
    if (isNullOrEmpty(apiKey)) {
      response.setStatus(SC_FORBIDDEN);
      return false;
    }
    var rateLimiting = redisTemplate.execute(rateLimitingScript, List.of(apiKey), "30", "10");
    if (FALSE.equals(rateLimiting)) {
      response.setStatus(TOO_MANY_REQUESTS.value());
      return false;
    }
    return true;
  }
}
