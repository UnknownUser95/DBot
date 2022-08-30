package net.unknownuser.dbot;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Config {
	public Config() {
		super();
		loadConfig();
		loadBackup();
	}
	
	private static HashMap<String, Object> config = new HashMap<>();
	private static HashMap<String, Object> config_bak = new HashMap<>();
	
	private void loadConfig() {
		try {
			
			// TODO: convert to jackson
			Files.readAllLines(Path.of("settings.json"));
			
			
		} catch(IOException exc) {
			System.err.println("error loading config");
			System.err.println(exc.getMessage());
		}
	}
	
	private void loadBackup() {
		try {
			
			// TODO: convert to jackson
			Files.readAllLines(Path.of("settings_default.json"));
			
		} catch(IOException exc) {
			System.err.println("error loadig backup settings");
			System.err.println(exc.getMessage());
		}
	}
	
	public Object get(String setting) {
		Object loaded = config.get(setting);
		return (loaded != null) ? loaded : config_bak.get(setting);
	}
}
