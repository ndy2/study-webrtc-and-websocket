package demo.chat.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.chat.core.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
