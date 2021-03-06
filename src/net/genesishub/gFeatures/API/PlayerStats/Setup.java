package net.genesishub.gFeatures.API.PlayerStats;

import java.io.File;
import java.io.IOException;

import net.genesishub.gFeatures.Basic;
import net.genesishub.gFeatures.Configuration.Config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Setup {
	public void checkPlayer(Player p){
		Config config = new Config();
		File f = new File("plugins/gFeatures/Players/" + p.getUniqueId().toString() + ".yml");
		if(!f.exists()){
			config.createFile("plugins/gFeatures/Players/" + p.getUniqueId().toString() + ".yml", "Made player directory!");
			
			gPlayer gp = new gPlayer(p);
			YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
			yamlFile.createSection("Config");
			yamlFile.createSection("Config.Name");
			yamlFile.set("Config.Name", p.getName());
			yamlFile.set("Config.UUID", p.getUniqueId().toString());
			for(String str : Basic.getPlayerSections().keySet()){
				yamlFile.createSection("Config." + str);
				yamlFile.set("Config." + str, Basic.getPlayerSections().get(str));
				gp.addValue(str, yamlFile.get("Config." + str).toString());
			}
			Basic.addgPlayer(gp);
			try {
				yamlFile.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
			gPlayer gp = Basic.getgPlayer(p.getUniqueId().toString());
			
			for(String str : Basic.getPlayerSections().keySet()){
				try{
				if(yamlFile.get("Config." + str).equals(null)){}
				}catch(Exception e){
					yamlFile.createSection("Config." + str);
					yamlFile.set("Config." + str, Basic.getPlayerSections().get(str));
					Bukkit.getLogger().info("Adding " + str);
					gp.addValue(str, yamlFile.get("Config." + str).toString());
				}
			}
			for(String str :  yamlFile.getConfigurationSection("Config").getKeys(true)){
				gp.setValue(str, yamlFile.get("Config." + str).toString());
			}
			try {
				yamlFile.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try{
				if(Basic.getgPlayer(p.getUniqueId().toString()).equals(null)){}
			}catch(Exception e){
				Basic.addgPlayer(gp);
			}
			Basic.setgPlayer(Basic.getgPlayer(p.getUniqueId().toString()), gp);
		}
	}
	public void flushPlayer(gPlayer p){
		File f = new File("plugins/gFeatures/Players/" + p.getUUID().toString() + ".yml");
		YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
		yamlFile.set("Config.Name", p.getName());
		for(String str : p.getValues().keySet()){
			try{
				if(yamlFile.get("Config." + str).equals(null)){}
			}
			catch(Exception e){
				yamlFile.createSection("Config." + str);
			}
				yamlFile.set("Config." + str, p.getValue(str));
			}
			try {
				yamlFile.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void smartFlush(gPlayer p, String valuename, String value){
			File f = new File("plugins/gFeatures/Players/" + p.getUUID() + ".yml");
			YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
			
			yamlFile.set("Config." + valuename, value);
			
			try {
				yamlFile.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
