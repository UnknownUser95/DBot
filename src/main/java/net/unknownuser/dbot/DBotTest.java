package net.unknownuser.dbot;

import java.awt.*;

import org.javacord.api.*;
import org.javacord.api.entity.message.embed.*;

public class DBotTest {
	// https://discord.com/api/oauth2/authorize?client_id=1012393292857622529&scope=applications.commands%20bot&permissions=8
	// invite link
	// numbers are application ID
	// permissions can be found under bot -> permissions
	
	public static void main(String[] args) {
		
		Config config = new Config();
		
		DiscordApi api = new DiscordApiBuilder().setToken((String) config.get("token")).login().join();
		
		System.out.printf("invite: \"%s\" %n", api.createBotInvite());
		
		api.addMessageCreateListener(event -> {
			if(event.getMessageAuthor().getId() != api.getClientId()) {
				event.getChannel().sendMessage(new EmbedBuilder().setTitle("neato").setDescription("magneto").setColor(Color.ORANGE));
			}
			
//			System.out.printf("%s: %s @%s%n", event.getMessageAuthor(), event.getMessageContent(), event.getChannel());
		});
	}
}
