package com.example.novelcharacter.configuration;

import com.example.novelcharacter.JWT.CustomLogoutFilter;
import com.example.novelcharacter.JWT.JWTFilter;
import com.example.novelcharacter.JWT.JWTUtil;
import com.example.novelcharacter.JWT.LoginFilter;
import com.example.novelcharacter.OAuth2.CustomSuccessHandler;
import com.example.novelcharacter.service.CustomOAuth2UserService;
import com.example.novelcharacter.service.RefreshService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final RefreshService refreshService;
    private final JWTUtil jwtUtil;

    @Autowired
    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, CustomOAuth2UserService customOAuth2UserService, CustomSuccessHandler customSuccessHandler,RefreshService refreshService, JWTUtil jwtUtil) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.customOAuth2UserService = customOAuth2UserService;
        this.customSuccessHandler = customSuccessHandler;
        this.refreshService = refreshService;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
//                    @Override
//                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//
//                        CorsConfiguration config = new CorsConfiguration();
//
//                        config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
//                        config.setAllowedMethods(Collections.singletonList("*"));
//                        config.setAllowCredentials(true);
//                        config.setAllowedHeaders(Collections.singletonList("*"));
//                        config.setMaxAge(3600L);
//
//                        config.setExposedHeaders(Collections.singletonList("Set-Cookie"));
//                        config.setExposedHeaders(Collections.singletonList("Authorization"));
//
//                        return config;
//                    }
//                }));

        http
                .csrf(auth -> auth.disable())
                .formLogin(auth -> auth.disable())
                .httpBasic(auth -> auth.disable())
                .addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshService), LogoutFilter.class)
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, this.passwordEncoder()), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new JWTFilter(jwtUtil), OAuth2LoginAuthenticationFilter.class)
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))
                        .successHandler(customSuccessHandler))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/api/**", "/login/**","/reissue").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;

        return http.build();
    }
}
