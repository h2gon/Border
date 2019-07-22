package com.example.demo.config;

import org.apache.tomcat.util.threads.TaskQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class Security extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 허용해야될 경로들
        web.ignoring().antMatchers("/resources/**","/user/join","/user/findpw");          
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // 로그인 설정
        http
            .authorizeRequests()    
                // ROLE_USER, ROLE_ADMIN으로 권한 분리            
                .antMatchers("/","/user/login").permitAll()
                .antMatchers("/**").access("ROLE_USER")
                .antMatchers("/**").access("ROLE_ADMIN")
                .antMatchers("/admin/**").access("ROLE_ADMIN")
                .antMatchers("/**").authenticated()
                .and()
                // 로그인 페이지 및 성공 url, handler 그리고 로그인 시 사용되는 id, password 파라미터 정의
            .formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/")                
                .usernameParameter("id")
                .passwordParameter("password")
                .and()
                // 로그아웃 관련 설정
            .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
            .authenticationProvider(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}