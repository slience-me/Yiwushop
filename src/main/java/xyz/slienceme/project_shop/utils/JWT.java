package xyz.slienceme.project_shop.utils;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author slience_me
 * @Time : 2021/7/16  8:31
 */
public class JWT {

    private static final Logger log = LoggerFactory.getLogger(JWT.class);

    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLDCQNCZQKunnjuiniujnk32234545fdf>?N<:{LWPW";

    private static ObjectMapper mapper = new ObjectMapper(); //jackson

    /**
     * header数据
     *
     * @return
     */
    private static Map<String, Object> createHead() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("typ", "JWT");
        map.put("alg", "HS256");
        return map;
    }

    /**
     * 生成token
     *
     * @param obj    对象数据
     * @param maxAge 有效期
     * @param <T>
     * @return
     */
    public static <T> String sign(T obj, long maxAge) throws UnsupportedEncodingException, JsonProcessingException {
        JWTCreator.Builder builder = com.auth0.jwt.JWT.create();

        builder.withHeader(createHead())//header
                .withSubject(mapper.writeValueAsString(obj));  //payload

        if (maxAge >= 0) {
            long expMillis = System.currentTimeMillis() + maxAge;
            Date exp = new Date(expMillis);
            builder.withExpiresAt(exp);
        }

        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 解密
     *
     * @param token  token字符串
     * @param classT 解密后的类型
     * @param <T>
     * @return
     */
    public static <T> T unsign(String token, Class<T> classT) {
        try {
            JWTVerifier verifier = com.auth0.jwt.JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);

            Date exp = jwt.getExpiresAt();
            if (exp != null && exp.after(new Date())) {
                String subject = jwt.getSubject();
                return mapper.readValue(subject, classT);
            }
        } catch (IOException IOE) {
            log.error("token解密报错：", IOE);
        }
        return null;
    }
}
