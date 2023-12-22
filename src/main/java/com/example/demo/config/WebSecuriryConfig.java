 package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.Imp.UserServiceImp;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecuriryConfig {

	public static final String[] PUBLIC_PATHS = {"/**","/css/**","/scripts/**","/images/**",
			"/", "/home", "/login", "/register", "/test/**", 
			"/cart/**",
			"/product/**" };
 
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new UserServiceImp();

	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// setting authen request
		http.authorizeHttpRequests((requests) -> requests.requestMatchers(PUBLIC_PATHS).permitAll().

				anyRequest().authenticated()).csrf((csrs) -> csrs.disable())

				.formLogin((form) -> form.loginPage("/login").defaultSuccessUrl("/home?message=success").permitAll()
						)
				.logout((logout) -> logout.permitAll())

				.rememberMe(remember -> remember.userDetailsService(userDetailsService()).tokenValiditySeconds(6000000))
				.sessionManagement(session -> session.invalidSessionUrl("/home").maximumSessions(1)
						.maxSessionsPreventsLogin(false).expiredUrl("/home"))

		;

		return http.build();

	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
