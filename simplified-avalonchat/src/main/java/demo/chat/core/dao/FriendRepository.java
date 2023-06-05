package demo.chat.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.chat.core.model.Friend;

public interface FriendRepository extends JpaRepository<Friend, Integer> {
}
