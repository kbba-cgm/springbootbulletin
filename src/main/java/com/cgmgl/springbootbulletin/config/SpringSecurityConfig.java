package com.cgmgl.springbootbulletin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.cgmgl.springbootbulletin.bl.service.Impl.MySimpleUrlAuthenticationSuccessHandler;
import com.cgmgl.springbootbulletin.bl.service.Impl.MyUserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    public UserDetailsService userDetailsService() {
        return new MyUserDetailServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorize -> authorize
                    .antMatchers("/register", "/denied").permitAll()
                    .antMatchers("/user/posts/*", "/user/posts/create", "/posts/store", "/user/posts/*/edit", "/posts/update", "/posts/*/delete").hasAnyAuthority("ADMIN", "USER")
                    .antMatchers("/home/**" , "/user/posts", "/profile/**").hasAnyAuthority("ADMIN", "USER")
                    .antMatchers("/**").hasAuthority("ADMIN"))
            .formLogin(form -> form
                    .loginPage("/login")
                    .usernameParameter("email").passwordParameter("password")
                    .successHandler(myAuthenticationSuccessHandler())
                    .permitAll())
            .logout(form -> form
                    .logoutSuccessUrl("/login"))
            .httpBasic();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/uploads/**");
    }

}
