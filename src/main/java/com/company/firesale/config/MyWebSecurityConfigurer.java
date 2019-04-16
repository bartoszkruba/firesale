package com.company.firesale.config;

import com.company.firesale.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private MyUserDetailsService myUserDetailsService;
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private MySavedRequestAwareAuthenticationSuccessHandler mySuccesHandler;
    private MySimpleUrlAuthenticationFailureHandler myFailureHandler;

    @Autowired
    public MyWebSecurityConfigurer(MyUserDetailsService myUserDetailsService,
                                   RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                                   MySavedRequestAwareAuthenticationSuccessHandler mySuccesHandler,
                                   MySimpleUrlAuthenticationFailureHandler myFailureHandler) {
        this.myUserDetailsService = myUserDetailsService;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.mySuccesHandler = mySuccesHandler;
        this.myFailureHandler = myFailureHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/api/test/**")
                .hasRole("USER")
                .and()
                .formLogin().permitAll()
                .successHandler(mySuccesHandler)
                .failureHandler(myFailureHandler)
                .loginProcessingUrl("/api/login")
                .and()
                .logout().logoutUrl("/api/logout")
                .and()
                .logout().permitAll().logoutSuccessUrl("/");
    }


    // pictures should lie in assets
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(myUserDetailsService)
                .passwordEncoder(myUserDetailsService.getEncoder());
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(myUserDetailsService.getEncoder().encode("password"))
                .roles("USER");

    }
}
