package pe.edu.utp.chat.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import pe.edu.utp.chat.models.documents.Mensaje;

@Controller
public class ChatController {

	private String[] colores = { "red", "green", "blue", "purple" };

	@MessageMapping("/mensaje") // en el cliente se agrega este prefijo en publich (para publica un mensaje)
	@SendTo("/chat/mensaje") // empieza con el prefijo chat y luego el nombre del evento, que se notifica a todos los cliente suscritos
	public Mensaje recibirMensaje(Mensaje mensaje) {
		
		mensaje.setFecha(new Date().getTime());
		
		if (mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("Nuevo usuario");
		}
		// mensaje.setTexto("Recibido por el Server: "+ mensaje.getTexto());
		return mensaje;
	}

	@MessageMapping("/escribiendo") // destino donde avisa o notifica al server cuando alguien escribe al chat
	@SendTo("/chat/escribiendo") // Notifica al resto de los usuarios
	public String estaEscribiendo(String username) {
		return username.concat(" esta escrbiendo...");
	}
}
