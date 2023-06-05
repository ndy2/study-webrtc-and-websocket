package demo.chat.config.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {

	public String name;

	public SecurityUser(String username, String name, String password) {
		super(username, password, AuthorityUtils.NO_AUTHORITIES);
		this.name = name;
	}
}
