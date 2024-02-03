package com.example.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    UserDetailsService CustomUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequest) ->
                        authorizeRequest
                                .requestMatchers("/api/auth/profile").authenticated()
                                //.requestMatchers("/admin").hasRole("ROLE_ADMIN")
                                .anyRequest().permitAll()
                )
                .formLogin(
                        httpSecurityFormLoginConfigurer ->
                                httpSecurityFormLoginConfigurer
                                        .loginProcessingUrl("/api/auth/login")
                                        .usernameParameter("userId")
                                        .passwordParameter("userPw")
                                        .permitAll()
                )
                .addFilterBefore(new CustomSecurityFilter(CustomUserDetailsService), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
