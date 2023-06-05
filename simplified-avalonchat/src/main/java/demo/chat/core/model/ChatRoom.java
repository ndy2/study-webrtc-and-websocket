package demo.chat.core.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ChatRoom {

	public ChatRoom() {
	}

	public ChatRoom(String name, String access) {
		this.name = name;
		this.access = access;
	}

	@Id
	public String roomId = UUID.randomUUID().toString();


	// 현재는 개발자가 식별할 수 있는 이름
	public String name;

	public String access;
}
