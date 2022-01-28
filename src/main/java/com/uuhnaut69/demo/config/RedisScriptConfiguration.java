package com.uuhnaut69.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

@Configuration
public class RedisScriptConfiguration {

  @Bean
  public RedisScript<Boolean> rateLimitingScript() {
    var scriptSource = new ResourceScriptSource(new ClassPathResource("static/scripts/rate_limiting.lua"));
    return RedisScript.of(scriptSource.getResource(), Boolean.class);
  }
}
