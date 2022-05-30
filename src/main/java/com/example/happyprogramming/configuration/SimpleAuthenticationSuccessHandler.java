package com.example.happyprogramming.configuration;

import com.example.happyprogramming.entity.UserEntity;
import com.example.happyprogramming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    UserService userService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity user = userService.findByEmail(email);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> {
            // nếu quyền có vai trò user, chuyển đến trang "/" nếu login thành công
            if (authority.getAuthority().equals("ROLE_MENTOR")) {
                try {
                    String sessionRole = "mentorAndMentee";
                    session.setAttribute("role",sessionRole);
                    session.setAttribute("userInformation",user);
                    redirectStrategy.sendRedirect(request, response, "/home");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if(authority.getAuthority().equals("ROLE_MENTEE")){
                try {
                    String sessionRole = "mentee";
                    session.setAttribute("role",sessionRole);
                    session.setAttribute("userInformation",user);
                    redirectStrategy.sendRedirect(request, response, "/home");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else if (authority.getAuthority().contains("ROLE_ADMIN")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/adminpage");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }  else {
                throw new IllegalStateException();
            }
        });

    }

}
