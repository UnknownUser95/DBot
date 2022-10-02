package net.unknownuser.dbot;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import org.javacord.api.*;
import org.javacord.api.entity.channel.*;
import org.javacord.api.entity.user.*;

import net.unknownuser.ansi.*;

public class DMChat {
	public void startChat(DiscordApi api, String userID) {
		try {
			User user = api.getUserById(userID).get();
			Optional<PrivateChannel> maybeChannel = user.openPrivateChannel().get().asPrivateChannel();
			if(!maybeChannel.isPresent()) {
				return;
			}
			
			PrivateChannel channel = maybeChannel.get();
			
			channel.addMessageCreateListener(event -> {
				if(event.getMessage().getUserAuthor().get().getId() != api.getClientId()) {
					System.out.printf("\r%s: %s%n# ", user.getName(), event.getMessageContent());
				}
			});
			
			System.out.print("# ");
			String input;
			StringBuilder alreadyInput = new StringBuilder();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				while(!(input = br.readLine()).equals("!exit")) {
					boolean canSend = true;
					if(input.endsWith("!!")) {
						canSend = false;
						alreadyInput = new StringBuilder();
					}
					if(input.endsWith("%%")) {
						canSend = false;
						alreadyInput.append(input.substring(0, input.length() - 2));
						System.out.println(Cursor.UP);
						System.out.print("\r# " + alreadyInput.toString());
					}
					
					if(canSend) {
						channel.sendMessage(alreadyInput.toString() + input);
						System.out.print("# ");
					}
				}
				System.out.println("exiting");
			} catch(IOException exc) {
				System.out.println("buffered reader crashed");
			}
		} catch(ExecutionException | InterruptedException exc) {
			exc.printStackTrace();
		}
	}
}
