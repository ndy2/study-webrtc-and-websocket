package demo.chat.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Friend {

	@Id
	public int id;

	// source user id
	public int myId;

	// dest user id
	public int friendId;

	// friend name
	// work as display name in friend view
	// can be set differently by each source user
	public String name;

	public Friend() {
	}

	public Friend(int id, int myId, int friendId, String name) {
		this.id = id;
		this.myId = myId;
		this.friendId = friendId;
		this.name = name;
	}
}
