package demo.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import demo.chat.message.Greeting;
import demo.chat.message.HelloMessage;

@Controller
public class GreetingController {

	//The @MessageMapping annotation ensures that, if a message is sent to the /hello destination, the greeting() method is called.
	@MessageMapping("/hello")
	//The return value is broadcast to all subscribers of /topic/greetings, as specified in the @SendTo annotation.
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws InterruptedException {
		Thread.sleep(1000);
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}
}
