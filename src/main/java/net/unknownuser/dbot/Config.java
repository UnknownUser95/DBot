package net.unknownuser.dbot;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;

public class Config {
	private HashMap<String, Object> settings = new HashMap<>();
	private HashMap<String, Object> fallback = new HashMap<>();
	
	private static final File FALLBACK_SETTINGS_FILE = new File("./settings_default.json");
	private static final File SETTINGS_fILE = new File("./settings.json");
	
	public Config() {
		super();
		loadConfig();
	}
	
	private void loadConfig() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			if(FALLBACK_SETTINGS_FILE.exists()) {
				fallback = mapper.readValue(FALLBACK_SETTINGS_FILE, new TypeReference<HashMap<String, Object>>() {});
			} else {
				System.err.println("fallback settings file does not exist");
			}
		} catch (IOException exc) {
			System.err.println("error while loading fallback configuration");
			System.err.println(exc.getMessage());
		}
		try {
			if(SETTINGS_fILE.exists()) {
				settings = mapper.readValue(SETTINGS_fILE, new TypeReference<HashMap<String, Object>>() {});
			} else {
				System.out.println("user settings file does not exist");
			}
		} catch(IOException exc) {
			System.err.println("error while loading user configuration");
			System.err.println(exc.getMessage());
		}
	}
	
	/**
	 * Updates the loaded settings.
	 */
	public void update() {
		loadConfig();
	}
	
	/**
	 * Get a setting associated to the key.
	 * 
	 * @param selector The key for the setting.
	 * @return The value mapped to the key, {@code null} if no value for the key exists.
	 */
	public Object get(String selector) {
		Object what = settings.get(selector);
		return (what != null) ? what : fallback.get(selector);
	}
	
	/**
	 * Get s setting associated to the key. If the key returns {@code null}, the given default is
	 * used.
	 * 
	 * @param selector      The key for the setting.
	 * @param defaultOnNull The default return if the key returns {@code null}.
	 * @return The value mapped to the key, or the given default value.
	 */
	public Object get(String selector, Object defaultOnNull) {
		Object obj = get(selector);
		return (obj != null) ? obj : defaultOnNull;
	}
}
