package net.unknownuser.dbot;

import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.concurrent.*;

import org.javacord.api.*;
import org.javacord.api.entity.message.*;
import org.javacord.api.entity.message.embed.*;

public class DBotTest {
	// https://discord.com/api/oauth2/authorize?client_id=1012393292857622529&scope=applications.commands%20bot&permissions=8
	// invite link
	// numbers are application ID
	// permissions can be found under bot -> permissions
	
	private static DiscordApi api;
	
	public static String loadToken() {
		try {
			return Files.readAllLines(Path.of("TOKEN")).get(0);
		} catch(IOException exc) {
			return "";
		}
	}
	
	public static void main(String[] args) {
		
		api = new DiscordApiBuilder().setToken(loadToken()).login().join();
		
		System.out.println("invite: " + api.createBotInvite());
		
		new Thread(DBotTest::cliStart).start();
		
		api.addMessageCreateListener(event -> {
			if(event.getMessageAuthor().getId() != api.getClientId()) {
				switch (event.getMessage().getContent()) {
				case "exit" -> {
					CompletableFuture<Message> msg = event.getChannel().sendMessage("exiting...");
					System.out.printf("exiting by %s%n", event.getMessageAuthor().getName());
					msg.join();
					api.disconnect();
				}
				case "neato" -> event.getChannel().sendMessage(new EmbedBuilder().setTitle("magneto").setColor(Color.ORANGE));
				case "hi" -> event.getChannel().sendMessage("hello!");
				default -> { /* any message without purpose */ }
				}
				
				System.out.printf("%s: %s @%s%n", event.getMessageAuthor(), event.getMessageContent(), event.getChannel());
			}
		});
		
		System.out.println("DBot started");
	}
	
	private static void cliStart() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line;
			while(!(line = br.readLine()).equals("exit")) {
				switch(line) {
				default -> System.out.println("unknown command");
				}
			}
			
			api.disconnect();
		} catch(IOException exc) {
			System.err.println("buffered reader error");
		}
	}
}
