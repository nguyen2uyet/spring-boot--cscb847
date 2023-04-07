package com.cscb847f89497.kursovarabota.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cscb847f89497.kursovarabota.impl.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/registration**",
                        "/register**",
                        "/js/**",
                        "/css/**",
                        "/img/**",
                        "/assets/**",
                        "/cscb847",
                        "/home",
                        "/languages/**",
                        "/programs/**",
                        "/about-us/**",
                        "/contact/**",
                        "/location/**",
                        "/currently-program/**",
                        "/soon-program/**",
                        "/lastyear-courses/**",
                        "/foregin-language/**",
                        "/bulgarian-language/**")
                .permitAll()
                .antMatchers("/").hasAnyAuthority("EDITOR", "ADMIN", "STUDENT")
                .antMatchers("/add/**", "/delete/**").hasAnyAuthority("ADMIN")
                .antMatchers("/edit/article/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/update/article/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/delete/article/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/add/article/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/articles/**").hasAnyAuthority("ADMIN", "EDITOR")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }
}
