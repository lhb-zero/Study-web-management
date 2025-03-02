package com.javaclimb;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@ServletComponentScan
@SpringBootApplication
public class StudyWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyWebManagementApplication.class, args);
    }

    /**
     * 生成jwt令牌
     */
    @Test
    public void testjwt(){
        Map<String, Object> clamis = new HashMap<>();
        clamis.put("id",1);
        clamis.put("name","wahaha");
        String Jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "cereshuzhitingnizhenbangcereshuzhitingnizhenbang")//签名算法和密钥
                .setClaims(clamis)//自定义部分（载荷）
                //设置有效期，用System.currentTimeMillis()获取当期时间毫秒形式，所以要*1000变成时钟，即1h
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();//生成返回值，即jwt令牌
        System.out.println(Jwt);
    }
        @Test
        public void testparsejwt(){
            Claims result = Jwts.parser()
                    .setSigningKey("cereshuzhitingnizhenbangcereshuzhitingnizhenbang")
                    .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoid2FoYWhhIiwiaWQiOjEsImV4cCI6MTc0MDMxMDg4OX0.jh72G_nwsQ54mar-Ygfz-5EoYg7OD7OD96Nf5UXUeHo")
                    .getBody();
            System.out.println(result);

        }

}
