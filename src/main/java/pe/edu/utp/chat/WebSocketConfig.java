package pe.edu.utp.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat-websocket") // unico endPoint para enviar y recibir mensajes
		.setAllowedOrigins("http://localhost:4200")
		.withSockJS(); // soporta http de lo contrario solo trabajariamos con ws
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/chat/"); // Prefijo para el nombre de los eventos
		registry.setApplicationDestinationPrefixes("/app"); // Prefijo para el destino donde se publica (El cliente)
	}
	
}
