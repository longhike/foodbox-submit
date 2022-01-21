package com.mphasis.foodbox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mphasis.foodbox.service.CustomUserDetailsService;

/**
* Configuration file for our SpringSecurity
*
*/

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	* Creates a new CustomUserDetailsService which Spring
	* Security will use
	* 
	* @return returns the CustomUserDetailsService
	*/
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	/**
	* Creates a new Password encoder which will be used
	* to encrypt/decrypt the user password and returns it
	*
	* @return returns the password encoder
	*/
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	* Creates a new authentication provider and adds
	* a userDetailsService and passwordEncoder
	*
	* @return returns the DaoAuthenticationProvider
	*/
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	/**
	* Calls authenticationprovicer
	*
	* @params Receives an authenticationManagerBuilder to be used to call
	* the authenticationProvider method
	*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	/**
	* Sets up configuration with how we will authorize requests made to the webpage
	*
	* @param Receives Http security
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin", "/admin/**", "/api/admin/**").hasRole("ADMIN")
				.antMatchers("/products", "/cart", "/api/products", "/api/products/**", "/api/cuisines",
						"/api/cuisines/**")
				.hasRole("USER").and().formLogin().loginPage("/login").and().csrf().disable();
	}
}
