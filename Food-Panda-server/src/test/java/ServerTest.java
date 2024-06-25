import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.taoding.Application;
import org.taoding.properties.JwtProperties;
import org.taoding.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = Application.class)
public class ServerTest {

    @Resource
    private JwtProperties jwtProperties;
    @Test
    public void test() {
        String key = jwtProperties.getAdminSecretKey();
        Map<String, Object> A = new HashMap<>();
        A.put("empId",1);
        String s = JwtUtil.createJWT(key, 1000000000, A);
        System.out.println("s = " + s);
        System.out.println();
        Claims c = JwtUtil.parseJWT(key, s);
        System.out.println("c.get = " + c);

    }
}