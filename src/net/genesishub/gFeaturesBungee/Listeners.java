package net.genesishub.gFeaturesBungee;

import net.md_5.bungee.api.plugin.Plugin;

public class Listeners extends Plugin{
	public static String version = "1.0.0";
	public void onEnable(){
		getLogger().info("_______________________________________________");
		getLogger().info("Enabling gFeaturesBungee version " + version + "...");
		getLogger().info("Enabled!");
		getLogger().info("_______________________________________________");
	}
	public void onDisable(){
		getLogger().info("_______________________________________________");
		getLogger().info("Disabling gFeaturesBungee version " + version + "...");
		getLogger().info("Disabled!");
		getLogger().info("_______________________________________________");
	}
}
