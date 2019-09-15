package pro.buildmysoftware.webflux.websocket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WebsocketNumbersHandlerTest {
	// @formatter:off
	@DisplayName(
		"when execute on /websocket/hello, " +
		"then sequence of 3 numbers is returned"
	)
	// @formatter:on
	@Test
	void test() throws Exception {
		// given
		Collection<String> messages = new ArrayList<>();
		WebSocketClient client = new ReactorNettyWebSocketClient();

		// when
		client.execute(new URI("ws://localhost:8080/websocket/hello"),
			session -> session
			.receive().map(WebSocketMessage::getPayloadAsText)
			.doOnNext(messages::add).then())
			.block(Duration.ofSeconds(5));

		// then
		assertThat(messages).containsExactlyInAnyOrder("1", "2", "3");
	}
}
