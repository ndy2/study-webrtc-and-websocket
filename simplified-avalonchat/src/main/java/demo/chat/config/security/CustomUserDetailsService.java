package demo.chat.config.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import demo.chat.core.dao.UserRepository;
import demo.chat.core.model.User;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		log.info("load user " + user);
		return new SecurityUser(user.username, user.name, user.password);
	}
}
