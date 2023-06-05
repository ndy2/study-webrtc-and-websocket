package demo.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import demo.chat.core.dao.FriendRepository;
import demo.chat.core.dao.UserRepository;
import demo.chat.core.model.Friend;
import demo.chat.core.model.User;

@Component
public class DataInitializer {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FriendRepository friendRepository;

	@Bean
	public ApplicationRunner dataInit() {
		return args -> {
			userRepository.save(new User(1, "user1", "신짱구", "password1", 11));
			userRepository.save(new User(2, "user2", "봉미선", "password2", 22));
			userRepository.save(new User(3, "user3", "김철수", "password3", 33));

			friendRepository.save(new Friend(100, 1, 2, "엄마"));
			friendRepository.save(new Friend(101, 1, 3, "철수"));

			friendRepository.save(new Friend(102, 2, 1, "아들"));
			friendRepository.save(new Friend(103, 2, 3, "짱구친구"));

			friendRepository.save(new Friend(104, 3, 1, "신짱구"));
			friendRepository.save(new Friend(105, 4, 2, "짱구맘"));
		};
	}
}
