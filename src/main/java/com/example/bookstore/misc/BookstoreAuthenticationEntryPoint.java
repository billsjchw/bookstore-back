package com.example.bookstore.misc;

import com.example.bookstore.dto.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.internal.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Component
@Primary
public class BookstoreAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Autowired private UserDetailsService userDetailsService;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException authenticationException) throws IOException, ServletException {
        String basic = httpServletRequest.getHeader("Authorization");
        String reason = findOutReason(basic);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        writer.print(objectMapper.writeValueAsString(new Message("UNAUTHORIZED", reason)));
    }

    private String findOutReason(String basic) {
        if (basic == null || !basic.startsWith("Basic "))
            return "MISSING_TOKEN";
        else {
            String base64String = basic.substring(5).trim();
            String account = new String(Base64.decode(base64String), StandardCharsets.UTF_8);
            String username = account.substring(0, account.indexOf(':'));
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                return userDetails.isEnabled() ? "WRONG_PASSWORD" : "DISABLED";
            } catch (UsernameNotFoundException e) {
                return "USER_NOT_FOUND";
            }
        }
    }
}
