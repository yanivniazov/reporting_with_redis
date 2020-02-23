package application.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
class AppConfig {

  @Bean
  public JedisConnectionFactory redisConnectionFactory() {
    return new JedisConnectionFactory();
  }
  
  @Bean
  public RedisTemplate<String, String> redisTemplate() {
      RedisTemplate<String, String> template = new RedisTemplate<>();
      template.setConnectionFactory(redisConnectionFactory());
      return template;
  }
}