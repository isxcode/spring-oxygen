package com.isxcode.ispring.filter;

import com.isxcode.ispring.common.BaseFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt 拦截器
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-16
 */
@Slf4j
@Component
public class JwtFilter extends BaseFilter {


    /**
     * 具体拦截的方法
     *
     * @since 2019-11-16
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        try{
//            String jwt = request.getHeader("token");
//            JwtUtils.decodeJwt(jwt);
//            // 验证成功放行

//        }catch (JwtException e){
//            throw new IsxcodeException("身份验证失败");
//        }

//        String header = request.getHeader("token");
//        if(header.equals("1")){
//            String userId = "1";
//        }
//        if(header.equals("2")){
//            String userId = "2";
//        }

        // 如何插入用户的信息到安全上传
        // 从token中拿取信息

        // 放行
        doFilter(request, response, filterChain);
    }
}
