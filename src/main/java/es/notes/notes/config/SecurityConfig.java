package es.notes.notes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

//funciona como una entrada principal
@Configuration
public class SecurityConfig {

    //que rutas estan operativas
    //donde esta la pagina de login
    //que pasa al hacer login y logout

    //encriptacion de contraseñas
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**","/js/**","/images/**","/login","/register").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/notes", true)
                
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login?logout").permitAll()
                
            )
            .csrf(Customizer.withDefaults());
            
        return http.build();
    }
}
