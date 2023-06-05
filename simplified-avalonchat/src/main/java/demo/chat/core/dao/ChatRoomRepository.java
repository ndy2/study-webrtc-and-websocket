package demo.chat.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.chat.core.model.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
}
