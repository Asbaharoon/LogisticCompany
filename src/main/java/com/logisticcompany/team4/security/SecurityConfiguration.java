package com.logisticcompany.team4.security;

import com.logisticcompany.team4.model.Employee;
import com.logisticcompany.team4.services.UserServices;
import constant.EmployeeType;
import constant.RoleType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.logisticcompany.team4.security.UserPrincipalDetailService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    public SecurityConfiguration(UserPrincipalDetailService userPrincipalDetailService, PasswordEncoder passwordEncoder) {
        this.userPrincipalDetailService = userPrincipalDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    private UserPrincipalDetailService userPrincipalDetailService;
    private PasswordEncoder passwordEncoder;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/register_success").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/companies/parcels").hasRole("EMPLOYEE")
                .antMatchers("/customers/parcels").hasRole("CUSTOMER")
                //reports antMatchers
                .antMatchers("/reports").hasAnyRole("ADMIN")
                .antMatchers("/users/**").hasRole("ADMIN")
                .antMatchers("/companies/**").hasRole("ADMIN")
                .antMatchers("/customerForms").hasRole("ADMIN")
                .antMatchers("/customers/**").hasRole("ADMIN")
                .antMatchers("/offices/**").hasRole("ADMIN")
                .antMatchers("/parcels").hasRole("ADMIN")
                .antMatchers("/employees/**").hasRole("ADMIN")
                .antMatchers("/register_employee").hasRole("ADMIN")

                .and()
                .formLogin()
                .loginProcessingUrl("/signin")
                .loginPage("/login").permitAll()
                .usernameParameter("txtUsername")
                .passwordParameter("txtPassword")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");

        http.csrf().disable();
        http.headers().frameOptions().disable();

    }


    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder.bCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailService);

        return daoAuthenticationProvider;
    }

}