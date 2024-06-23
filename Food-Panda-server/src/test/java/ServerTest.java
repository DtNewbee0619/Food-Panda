import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.taoding.SkyApplication;
import org.taoding.properties.JwtProperties;
import org.taoding.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = SkyApplication.class)
public class ServerTest {

    @Resource
    private JwtProperties jwtProperties;
    @Test
    public void test() {
        String key = jwtProperties.getAdminSecretKey();
        Map<String, Object> A = new HashMap<>();
        A.put("empId",1);
/*
        String s = JwtUtil.createJWT(key, 100000000, A);
        System.out.println("s = " + s);
        System.out.println();*/
        String s = "eyJhbGciOiJIUzM4NCJ9.eyJlbXBJZCI6MSwiZXhwIjoxNzE5MjM5MzY4fQ.FJCCMy2egdmOZeYVZF0yQFltyibslzjbFULff78_7kxJi6wERzw9XK_t4IZgH6MK";
        Claims c = JwtUtil.parseJWT(key, s);
        System.out.println("c.get = " + c);

    }
}