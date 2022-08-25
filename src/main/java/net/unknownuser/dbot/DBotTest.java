package net.unknownuser.dbot;

import javax.security.auth.login.*;

import org.javacord.api.*;

public class DBotTest {
	// https://discord.com/api/oauth2/authorize?client_id=1012393292857622529&scope=applications.commands%20bot&permissions=8
	// invite link
	// numbers are application ID
	// permissions can be found under bot -> permissions
	
	private static final String API_TOKEN = "MTAxMjM5MzI5Mjg1NzYyMjUyOQ.GEB7uC.yEer12ScqZHV2muygdBqZPstPFA2_62sVAfg98";
	
	public static void main(String[] args) throws LoginException {
		DiscordApi api = new DiscordApiBuilder().setToken(API_TOKEN).login().join();
		
		api.addMessageCreateListener(event -> {
//			if(event.getMessageContent().equalsIgnoreCase("!ping")) {
//				event.getChannel().sendMessage("Pong!");
//			}
			System.out.printf("%s: %s @%s%n", event.getMessageAuthor(), event.getMessageContent(), event.getChannel().getType());
		});
		
		System.out.println("invite: " + api.createBotInvite());
		
//		JDABuilder builder = JDABuilder.createDefault(API_TOKEN);
//    
//    // Disable parts of the cache
//    builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
//    // Enable the bulk delete event
//    builder.setBulkDeleteSplittingEnabled(false);
//    // Disable compression (not recommended)
//    builder.setCompression(Compression.NONE);
//    // Set activity (like "playing Something")
//    builder.setActivity(Activity.watching("TV"));
//    
//    builder.build();
	}
}
