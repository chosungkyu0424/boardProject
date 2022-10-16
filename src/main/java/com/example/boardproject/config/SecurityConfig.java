package com.example.boardproject.config;

import com.example.boardproject.config.auth.PrincipalDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalDetailService principalDetailService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //시큐리티가 대신 로그인할때 password를 가로채기하는데 해당 password가 뭘로 해시가 되어 회원가입이 되었는지 알아야
    //같은 해시로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin() //권한이 없는 사람이 페이지를 이동하려고 하면 로그인 페이지로 이동
                .loginPage("/auth/user/login") //해당하는 로긍니 페이지 URL로 이동
                .loginProcessingUrl("/auth/user/login") //스프링시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인
                .defaultSuccessUrl("/"); //로그인이 성공하면 해당 url로 이동
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}