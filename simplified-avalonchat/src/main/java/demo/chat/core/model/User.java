package demo.chat.core.model;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	public static PasswordEncoder passwordEncoder;

	@Id
	public int id;

	public String username;

	public String name;
	public String password;
	public int age;

	public User() {
	}

	public User(int id, String username, String name, String password, int age) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.age = age;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("User{");
		sb.append("id=").append(id);
		sb.append(", username='").append(username).append('\'');
		sb.append(", name='").append(name).append('\'');
		sb.append(", password='").append(password).append('\'');
		sb.append(", age=").append(age);
		sb.append('}');
		return sb.toString();
	}

	@PrePersist
	public void encodePassword() {
		this.password = passwordEncoder.encode(password);
	}
}
