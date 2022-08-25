package net.unknownuser.dbot;

import java.awt.*;
import java.io.*;
import java.nio.file.*;

import javax.security.auth.login.*;

import org.javacord.api.*;
import org.javacord.api.entity.message.embed.*;

public class DBotTest {
	// https://discord.com/api/oauth2/authorize?client_id=1012393292857622529&scope=applications.commands%20bot&permissions=8
	// invite link
	// numbers are application ID
	// permissions can be found under bot -> permissions
	
	public static String loadToken() {
		try {
			return Files.readAllLines(Path.of("TOKEN")).get(0);
		} catch(IOException exc) {
			return "";
		}
	}
	
	public static void main(String[] args) {
		
		DiscordApi api = new DiscordApiBuilder().setToken(loadToken()).login().join();
		
		System.out.println("invite: " + api.createBotInvite());
		
		api.addMessageCreateListener(event -> {
			if(event.getMessageAuthor().getId() != api.getClientId()) {
				event.getChannel().sendMessage(new EmbedBuilder().setTitle("neato").setDescription("magneto").setColor(Color.ORANGE));
			}
			
//			System.out.printf("%s: %s @%s%n", event.getMessageAuthor(), event.getMessageContent(), event.getChannel());
		});
	}
}
