import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.taoding.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 6/23/24 13:59
 * @Author Tao Ding
 * @Description: TODO
 */
public class sds {
    @Test
    public void test() {
        Map<String, Object> A = new HashMap<>();
        A.put("id", "DtNewbee");
        String key = "DtNewbee";
        String s = JwtUtil.createJWT(key, 100000000, A);
        System.out.println("s = " + s);
        Claims c = JwtUtil.parseJWT(key, s);
        System.out.println("c.get = " + c);

    }
}
