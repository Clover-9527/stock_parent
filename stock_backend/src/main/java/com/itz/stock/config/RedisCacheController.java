package com.itz.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisCacheController {
    /**
     * 自定义key序列化
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)  {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        //自定义key的序列化工具对象

        //设置redis中key的序列化
        template.setKeySerializer(stringRedisSerializer());
        //设置hash中field序列化
        template.setHashKeySerializer(stringRedisSerializer());
        //指定value的序列化方式
        template.setValueSerializer(stringRedisSerializer());
        template.setHashValueSerializer(stringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public StringRedisSerializer stringRedisSerializer(){
        return new StringRedisSerializer();
    }

}
