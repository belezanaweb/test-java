package br.com.blz.testjava.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * Security class.
 * 
 * @author Andre Barroso
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
   
	 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
        auth.inMemoryAuthentication()
          .withUser("user1").password(passwordEncoder().encode("user1Pass"))
          .authorities("ROLE_USER");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        	.csrf().disable()
        	.authorizeRequests()
        	.anyRequest().authenticated()
        	.and()
        	.httpBasic();
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    
}
