package com.company.firesale.config;

import com.company.firesale.config.handlers.MySavedRequestAwareAuthenticationSuccessHandler;
import com.company.firesale.config.handlers.MySimpleUrlAuthenticationFailureHandler;
import com.company.firesale.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

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
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .formLogin().permitAll()
                .successHandler(mySuccesHandler)
                .failureHandler(myFailureHandler)
                .loginProcessingUrl("/api/login")
                .and()
                .logout().logoutUrl("/api/logout").permitAll()
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK));

        declareSecuredRoutes(http);
    }


    private void declareSecuredRoutes(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/test/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/admintest").hasAnyRole("ADMIN")
                .antMatchers("/api/usertest").hasAnyRole("USER", "ADMIN");
    }

    // pictures should be in assets
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
