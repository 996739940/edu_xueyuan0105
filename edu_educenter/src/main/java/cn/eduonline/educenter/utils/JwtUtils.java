package cn.eduonline.educenter.utils;

import cn.eduonline.educenter.entity.UcenterMember;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @ClassName JwtUtils
 * @Description 单点登录加密方式
 * @Author 张燕廷
 * @Date 2020/3/3 17:24
 * @Version 1.0
 **/
public class JwtUtils {

    public static final String SUBJECT = "guli";

    //秘钥
    public static final String APPSECRET = "guli";

    public static final long EXPIRE = 1000 * 60 * 30;  //过期时间，毫秒，30分钟


    /**
     * 生成jwt token
     *
     * @param member
     * @return
     */
    public static String geneJsonWebToken(UcenterMember member) {

        if (member == null || StringUtils.isEmpty(member.getId())
                || StringUtils.isEmpty(member.getNickname())
                || StringUtils.isEmpty(member.getAvatar())) {
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", member.getId())
                .claim("nickname", member.getNickname())
                .claim("avatar", member.getAvatar())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();

        return token;
    }

    /**
     * 校验jwt token
     *
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
        return claims;
    }




    //测试生成jwt token
    private static String testGeneJwt(){
        UcenterMember member = new UcenterMember();
        member.setId("999");
        member.setAvatar("www.guli.com");
        member.setNickname("Helen");

        String token = JwtUtils.geneJsonWebToken(member);
        System.out.println(token);
        return token;
    }

    //测试校验jwt token
    private static void testCheck(String token){

        Claims claims = JwtUtils.checkJWT(token);
        String nickname = (String)claims.get("nickname");
        String avatar = (String)claims.get("avatar");
        String id = (String)claims.get("id");
        System.out.println(nickname);
        System.out.println(avatar);
        System.out.println(id);
    }


    public static void main(String[] args){
        //测试传入对象生成token值
        UcenterMember member = new UcenterMember();
        member.setId("123");
        member.setNickname("草上飞");
        member.setAvatar("ererete333");

        String token = geneJsonWebToken(member);
        System.out.println(token);

        //根据token值获取token里面内容
        Claims claims = checkJWT(token);
        String id  = (String)claims.get("id");
        String nickname = (String)claims.get("nickname");
        String avatar = (String)claims.get("avatar");

        System.out.println(id);
        System.out.println(nickname);
        System.out.println(avatar);

    }
}
