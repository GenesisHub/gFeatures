package net.genesishub.gFeaturesSponge;

import java.util.List;

import net.genesishub.gFeaturesSponge.API.PlayerStats.ConfigHub;
import net.genesishub.gFeaturesSponge.Configuration.LoadConfig;
import net.genesishub.gFeaturesSponge.Configuration.SetupConfig;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/*
gFeatures
https://github.com/GenesisHub/gFeatures

   Copyright 2015 GenesisHub

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

public class CoreCommands{
	CommandLibrary cl = new CommandLibrary();
	public void onCommand(final CommandSender sender, Command cmd, String label, String[] args){
		try {
				if(args.length == 0){
					if(cmd.getName().equalsIgnoreCase("gf") || cmd.getName().equalsIgnoreCase("gfeatures")){
						sender.sendMessage(ChatColor.GRAY + "Please do /gFeatures help.");
					}
				}
				else if(args.length == 1){
					switch(args[0]){
					case "version":
						sender.sendMessage(ChatColor.GRAY + "gFeatures Version " + Listeners.version);
						break;
					case "help":
						sender.sendMessage(ChatColor.GRAY + "------Help------");
						sender.sendMessage(ChatColor.GRAY + "/gFeatures version : States the version.");
						sender.sendMessage(ChatColor.GRAY + "/gFeatures list : Lists all features with their states and versions also.");
						sender.sendMessage(ChatColor.GRAY + "/gFeatures featurestate <Feature> : Gets the state of the feature.");
						sender.sendMessage(ChatColor.GRAY + "/gFeatures reload : Reloads the plugin.");
						break;
					case "list":
						List<gFeature> features = Basic.getFeatures();
						List<Extension> extensions = Basic.getExtensions();
						sender.sendMessage(ChatColor.GRAY + "Features:");
						sender.sendMessage(ChatColor.GRAY + "Enabled:");
						for(gFeature feature : features){
							if(feature.getState().equals(FeatureState.ENABLE)){
							sender.sendMessage(ChatColor.GRAY + " - " + feature.getName() + " " + feature.getVersion());
							}
						}
						sender.sendMessage(ChatColor.GRAY + "Disabled:");
						for(gFeature feature : features){
							if(feature.getState().equals(FeatureState.DISABLE)){
							sender.sendMessage(ChatColor.GRAY + " - " + feature.getName() + " " + feature.getVersion());
							}
						}
						sender.sendMessage(ChatColor.GRAY + "Extensions:");
						sender.sendMessage(ChatColor.GRAY + "Enabled:");
						for(Extension extension : extensions){
							if(extension.getState().equals(FeatureState.ENABLE)){
								sender.sendMessage(ChatColor.GRAY + " - " + extension.getName() + " " + extension.getVersion());
							}
						}
						sender.sendMessage(ChatColor.GRAY + "Disabled:");
						for(Extension extension : extensions){
							if(extension.getState().equals(FeatureState.DISABLE)){
								sender.sendMessage(ChatColor.GRAY + " - " + extension.getName() + " " + extension.getVersion());
							}
						}
						break;
					case "featurestate":
						sender.sendMessage(ChatColor.GRAY + "Usage: /gFeatures featurestate <Plugin>");
						break;
					case "reload":
						Enabler enable = new Enabler();
						Disabler disable = new Disabler();
						Setup setup = new Setup();
						Bukkit.getLogger().info("_________________________________________________________________________");
						Bukkit.getLogger().info("[gFeatures] gFeatures disabled!");
						Bukkit.getLogger().info("[gFeatures] This gFeatures installation is running core: " + Listeners.version);
						Bukkit.getLogger().info("[gFeatures] Turning off Features...");
						disable.onDisable();
						Bukkit.getLogger().info("[gFeatures] Complete!");
						Bukkit.getLogger().info("_________________________________________________________________________");
						Bukkit.getLogger().info("_________________________________________________________________________");
						Bukkit.getLogger().info("[gFeatures] gFeatures enabled!");
						Bukkit.getLogger().info("[gFeatures] This gFeatures installation is running core: " + Listeners.version);
						Bukkit.getLogger().info("[gFeatures] Turning on Features...");
						setup.onSetup();
						SetupConfig.setup();
						LoadConfig.load();
						enable.onEnable();
						Basic.addPlayerSection("Setup", "DO NOT REMOVE!");
						ConfigHub ch = new ConfigHub();
						ch.setupConfig();
						ch.loadConfig();
						Bukkit.getLogger().info("[gFeatures] Complete!");
						Bukkit.getLogger().info("_________________________________________________________________________");
						sender.sendMessage(ChatColor.GRAY + "Reload complete.");
						break;
					default:
						if(cmd.getName().equalsIgnoreCase("gf") || cmd.getName().equalsIgnoreCase("gfeatures")){
						sender.sendMessage(ChatColor.GRAY + "Please do /gFeatures help.");
						}
						break;
					}
				}
				else if(args.length == 2){
					switch(args[0]){
					case "featurestate":
						gFeature feature = Basic.getFeature(args[1]);
						sender.sendMessage(ChatColor.GRAY + "Feature " + args[1] + " state is " + feature.getState().toString());
						break;
					default:
						if(cmd.getName().equalsIgnoreCase("gf") || cmd.getName().equalsIgnoreCase("gfeatures")){
							sender.sendMessage(ChatColor.GRAY + "Please do /gFeatures help.");
						}
						break;
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
