package org.example.blogtemplate.Security;

import org.example.blogtemplate.DAO.UserRepository;
import org.example.blogtemplate.Service.BlogUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BlogSecurity {

    private UserRepository userRepository;
    @Autowired
    public BlogSecurity(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new BlogUserDetailService(userRepository);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http.authorizeRequests().requestMatchers("/blog/*").authenticated().requestMatchers("/contact").hasAuthority("ADMIN").and().formLogin().loginPage("/loginform").defaultSuccessUrl("/",true).loginProcessingUrl("/login").failureUrl("/error").permitAll();
        return http.build();
    }


}
