package net.genesishub.gFeatures.API.PlayerStats;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import net.genesishub.gFeatures.Basic;
import net.genesishub.gFeatures.Configuration.Config;

public class Load {
	File f = new File("plugins/gFeatures/Players");
	public void load(){
		Config config = new Config();
		config.createDirectory("plugins/gFeatures/Players", "Players directory created!");
		for(File file : f.listFiles()){
			YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(file);
			gPlayer gp = new gPlayer(yamlFile.get("Config.UUID").toString(), yamlFile.get("Config.Name").toString());
			for(String str :  yamlFile.getConfigurationSection("Config").getKeys(true)){
				gp.addValue(str, yamlFile.get("Config." + str).toString());
			}
			for(String str : Basic.getPlayerSections().keySet()){
				try{
					if(yamlFile.get("Config." + str).equals(null)){}
				}
				catch(Exception e){
					Bukkit.getLogger().info("Found a problem with the file! Attempting to patch " + str);
					yamlFile.createSection("Config." + str);
					yamlFile.set("Config." + str, Basic.getPlayerSections().get(str));
					gp.addValue(str, yamlFile.get("Config." + str).toString());
				}
			}
			try {
				yamlFile.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Basic.addgPlayer(gp);
		}
	}
}
