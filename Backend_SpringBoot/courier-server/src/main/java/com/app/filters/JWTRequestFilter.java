package com.app.filters;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.jwt_utils.JwtUtils;

@Slf4j
@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils utils; // Utility class for handling JWT operations

    @Autowired
    private UserDetailsService userDetailsService; // Service to load user details

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("In once per request filter");

        // Get the Authorization header and check if it starts with "Bearer "
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            // Extract and validate the JWT token
            String token = header.substring(7); // Remove "Bearer " prefix
            if (utils.validateJwtToken(token)) {
                // Extract username from the token
                String userName = utils.getUserNameFromJwtToken(token);

                if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Load user details from UserDetailsService
                    UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                    // Create Authentication object
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                    // Save the authentication token in the Security Context
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    log.info("User name null or authentication already set, username {}", userName);
                }
            } else {
                log.error("Invalid JWT token: {}", token);
            }
        } else {
            log.error("Request header DOES NOT contain a Bearer Token");
        }

        // Pass the request to the next filter in the chain
        filterChain.doFilter(request, response);
    }
}
