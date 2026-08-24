package br.com.clyvo.petguardian.config;

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
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Rotas públicas
                        .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register").permitAll()
                        .requestMatchers("/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/error").permitAll()

                        // Permissões de Pets
                        .requestMatchers(HttpMethod.GET, "/pets/**").hasAnyRole("RESPONSIBLE", "VETERINARIAN", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/pets/**").hasAnyRole("RESPONSIBLE", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pets/**").hasAnyRole("RESPONSIBLE", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pets/**").hasAnyRole("RESPONSIBLE", "ADMIN")

                        // Permissões de Consultas (Appointments)
                        .requestMatchers(HttpMethod.GET, "/appointments/**").hasAnyRole("RESPONSIBLE", "VETERINARIAN", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/appointments/**").hasAnyRole("VETERINARIAN", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/appointments/**").hasAnyRole("VETERINARIAN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/appointments/**").hasAnyRole("VETERINARIAN", "ADMIN")

                        // Qualquer outra requisição precisa apenas estar autenticada
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Permite o H2 Console
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}