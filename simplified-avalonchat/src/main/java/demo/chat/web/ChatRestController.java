package demo.chat.web;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.chat.config.security.SecurityUser;
import demo.chat.core.dao.ChatRoomRepository;
import demo.chat.core.model.ChatRoom;

@RequestMapping("/chat")
@RestController
public class ChatRestController {

	@Autowired
	public ChatRoomRepository chatRoomRepository;

	private Logger log = LoggerFactory.getLogger(ChatRestController.class);

	@PostMapping("/room")
	public ChatRoom obtainChatRoom(
		@AuthenticationPrincipal SecurityUser principal,
		@RequestParam String name
	) {
		log.info("obtain (get or create) chat room request from {}, on friend {}", principal.name, name);
		ChatRoom chatRoom = new ChatRoom(
			chatRoomTitle(principal, name),
			String.join(" ", principal.name, name)
		);
		chatRoomRepository.save(chatRoom);
		return chatRoom;
	}

	private static String chatRoomTitle(SecurityUser principal, String friendName) {
		return "ChatRoom between " + " [" + principal.name + "]" + " and [" + friendName + "]";
	}

	@GetMapping("/rooms")
	public List<ChatRoom> getAllChatRooms(
		@AuthenticationPrincipal SecurityUser principal
	) {
		log.info("get all chat room invoked by {}", principal.name);

		Predicate<ChatRoom> accessiblePredicate = cr ->
			Arrays.stream(cr.access.split(" "))
				.anyMatch(a -> a.equals(principal.name));

		return chatRoomRepository
			.findAll().stream().filter(accessiblePredicate)
			.toList();
	}

	@GetMapping("/room/{roomId}")
	public ChatRoom getById(
		@AuthenticationPrincipal SecurityUser principal,
		@PathVariable String roomId
	) {
		log.info("get all chat room invoked by {}", principal.name);

		return chatRoomRepository.findById(roomId).orElseThrow();
	}
}
