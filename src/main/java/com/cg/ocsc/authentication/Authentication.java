package com.cg.ocsc.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class Authentication extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("Shruthi").password("{noop}abin").roles("ADMIN")
            .and()
            .withUser("Shailesh").password("{noop}shikhar").roles("OPERATOR")
            .and()
            .withUser("Soumya").password("{noop}suresh").roles("CUSTOMER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
         .csrf().disable()
         /*.logout()
         .invalidateHttpSession(true)
         .and()*/
         .authorizeRequests()
         .antMatchers("/", "/issue/create").permitAll()
         .antMatchers("/updateissue/*", "/closeissue/{id}", "/fetchcustomerbyid/*", "/fetchcustomerbyname/*", "/fetchcustomerbymail/*", "/sendIntimationEmail/*/*", "/sendModificationEmail/*/*").hasAnyRole("OPERATOR","ADMIN")
         .antMatchers("/department/add", "/department/delete/*",  "/department/update/*", "/departmentById/*", "/operator/add", "/operator/delete/*", "/operator/update/*", "/operatorById/*", "/operator/all").hasRole("ADMIN")
         .anyRequest().authenticated()
         .and()
         .httpBasic();
    }
}
