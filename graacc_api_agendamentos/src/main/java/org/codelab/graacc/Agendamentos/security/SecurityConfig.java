package org.codelab.graacc.Agendamentos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated()
                                .requestMatchers(HttpMethod.GET, "/hello").permitAll()
                                .requestMatchers(HttpMethod.GET, "/agendamentos/hello").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/agendamentos/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.GET, "/agendamentos/usuario").hasAnyRole("USER")
                                .requestMatchers(HttpMethod.POST, "/agendamentos/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/agendamentos/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/agendamentos/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/pacientes/pesquisar").permitAll()
                                .requestMatchers(HttpMethod.POST, "/pacientes").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/pacientes").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/pacientes/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/pacientes/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
