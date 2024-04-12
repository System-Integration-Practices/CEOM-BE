package com.example.ceom.security.jwt;

import com.example.ceom.security.service.UserDetailsImpl;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
          throws AuthenticationException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                          FilterChain chain, Authentication authResult)
          throws IOException, ServletException {
    UserDetails userDetails = (UserDetails) authResult.getPrincipal();

    // Tạo JWT
    String token = jwtUtils.generateTokenFromUsername(userDetails.getUsername(), String.valueOf(userDetails.getAuthorities()));

    // Tạo và gửi cookie JWT
    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie((UserDetailsImpl) userDetails);
    response.addHeader("Set-Cookie", jwtCookie.toString());

    response.addHeader("Authorization", "Bearer " + token);
  }
}
