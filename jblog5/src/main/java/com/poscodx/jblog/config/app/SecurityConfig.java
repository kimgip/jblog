package com.poscodx.jblog.config.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.poscodx.jblog.security.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return new WebSecurityCustomizer() {
            @Override
            public void customize(WebSecurity web) {
                web
            		.ignoring()
            		.requestMatchers(new AntPathRequestMatcher("/favicon.ico"))
                	.requestMatchers(new AntPathRequestMatcher("/assets/**"));
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
    		.logout()
    		.logoutUrl("/user/logout")
    		.logoutSuccessUrl("/")
    		.and()
    		
    		.formLogin()
    		.loginPage("/user/login")
    		.loginProcessingUrl("/user/auth")
    		.usernameParameter("email")
    		.passwordParameter("password" )
    		.defaultSuccessUrl("/")
    		.failureUrl("/user/login?result=fail");
    		
        return http.build();
    }
    
    // Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
    	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    	authenticationProvider.setPasswordEncoder(passwordEncoder);
    	authenticationProvider.setUserDetailsService(userDetailsService);
    	return new ProviderManager(authenticationProvider);
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder(16);
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
    	return new UserDetailsServiceImpl();
    }
}