package demo.chat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import demo.chat.config.security.CustomUserDetailsService;
import demo.chat.core.dao.UserRepository;
import demo.chat.core.model.User;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	static {
		User.passwordEncoder = NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(
		HttpSecurity http
	) throws Exception {
		return http
			.csrf(c -> c.disable())
			.formLogin(f ->
				f.successHandler(new SimpleUrlAuthenticationSuccessHandler("/chat/room"))
			)
			.authorizeHttpRequests(a -> a.anyRequest().authenticated())
			.build();
	}

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository) {
		return new CustomUserDetailsService(userRepository);
	}

	@Autowired
	public void configureGlobal(
		AuthenticationManagerBuilder auth,
		UserDetailsService userDetailsService
	) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
}
