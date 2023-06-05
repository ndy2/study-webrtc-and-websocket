package demo.chat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.chat.config.security.SecurityUser;

@RequestMapping("/chat")
@Controller
public class ChatViewController {

	private Logger log = LoggerFactory.getLogger(ChatRestController.class);

	@GetMapping("/room")
	public String chatRoomView(
		@AuthenticationPrincipal SecurityUser principal,
		Model model
	) {
		log.info("user : {}, enter chat room view", principal.name);
		model.addAttribute("username", principal.name);
		return "/chat/room";
	}

	@GetMapping("/room/enter/{roomId}")
	public String chatRoomDetailsView(
		Model model,
		@PathVariable String roomId
	) {
		model.addAttribute("roomId", roomId);
		return "/chat/roomdetail";
	}
}
