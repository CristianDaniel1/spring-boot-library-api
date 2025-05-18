package github.libraryapi.config;

import github.libraryapi.security.LoginSocialSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http, LoginSocialSuccessHandler successHandler) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(configurer -> {
                    configurer.loginPage("/login");
                })
//                .formLogin(configurer -> configurer.loginPage("/login.html").successForwardUrl("/home.html"))
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
//                    authorize.requestMatchers(HttpMethod.POST,"/autores/**").hasAuthority("CADASTRAR_AUTOR");
//                    authorize.requestMatchers(HttpMethod.POST,"/autores/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.DELETE,"/autores/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.PUT,"/autores/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.GET,"/autores/**").hasAnyRole("ADMIN", "USER");
                    authorize.requestMatchers("/login/**").permitAll();
                    authorize.requestMatchers(HttpMethod.POST,"/usuarios/**").permitAll();
//                    authorize.requestMatchers("/autores/**").hasRole("ADMIN");
//                    authorize.requestMatchers("/livros/**").hasAnyRole("USER", "ADMIN");

                    authorize.anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> {
                    oauth2
                        .loginPage("/login")
                        .successHandler(successHandler);
                })
                .build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);
//    }

//    @Bean
//    public UserDetailsService userDetailsService(UsuarioService usuarioService) {

//        UserDetails user1 = User.builder()
//                .username("usuario")
//                .password(encoder.encode("123"))
//                .roles("USER")
//                .build();
//
//        UserDetails user2 = User.builder()
//                .username("admin")
//                .password(encoder.encode("321"))
//                .roles("ADMIN")
//                .build();

//        return new CustomUserDetailsService(usuarioService);
//    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }
}
