package com.nunegal.timeTracking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.nunegal.timeTracking.services.EmployeeService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private EmployeeService userDetailsService;	

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf(csrf -> csrf.disable())			
			.authorizeHttpRequests(auth -> auth
			.requestMatchers("/login", "/css/**", "/js/**").permitAll()		
			//.requestMatchers(HttpMethod.POST, "/employees/test").permitAll()
			.anyRequest().authenticated())
			
			.formLogin(form -> form
					.loginPage("/login")
					.loginProcessingUrl("/login")
					.failureUrl("/login?error=true")
					.permitAll())
			
			.logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login?logout=true")
					.permitAll());
		
		return httpSecurity.build();

	}
           
    
    @Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticacionConfiguration) throws Exception {
		
		return authenticacionConfiguration.getAuthenticationManager();
	} 
   
}
