package org.taoding.test;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.Set;

/**
 * @Date 6/27/24 15:14
 * @Author Tao Ding
 * @Description: TODO
 */
@SpringBootTest
public class SpringDataRedisTest {
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisTemplate(){
        //Set keys = redisTemplate.keys("dish_*");
        //System.out.println("keys = " + keys);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String dish11 = (String) valueOperations.get("dish_11");
        System.out.println("dish11 = " + dish11);
    }
    
    @Test
    public void StringTest(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("name","tao");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    public void HashTest(){
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("customer","name","tao");
        hashOperations.put("customer","age","18");
        String age = (String) hashOperations.get("customer", "age");
        System.out.println(age);
        Set customerKey = hashOperations.keys("customer");
        System.out.println(customerKey);
        List customerValue = hashOperations.values("customer");
        System.out.println(customerValue);
        Long CustomerSize = hashOperations.size("customer");
        System.out.println(CustomerSize);

    }
}
