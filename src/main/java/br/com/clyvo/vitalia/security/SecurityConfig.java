package br.com.clyvo.vitalia.security;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Rotas públicas
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register/vet").permitAll()
                        .requestMatchers("/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers("/error").permitAll()

                        // Permissões de Pets
                        .requestMatchers(HttpMethod.GET, "/pets/**").hasAnyRole("TUTOR", "VETERINARIAN", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/pets/**").hasAnyRole("TUTOR", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pets/**").hasAnyRole("TUTOR", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pets/**").hasAnyRole("TUTOR", "ADMIN")

                        // Permissões de Consultas (Appointments)
                        .requestMatchers(HttpMethod.GET, "/appointments/**").hasAnyRole("TUTOR", "VETERINARIAN", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/appointments/**").hasAnyRole("VETERINARIAN", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/appointments/**").hasAnyRole("VETERINARIAN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/appointments/**").hasAnyRole("VETERINARIAN", "ADMIN")

                        // Qualquer outra requisição precisa apenas estar autenticada
                        .anyRequest().authenticated()
                )
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