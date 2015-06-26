package net.genesishub.gFeaturesBungee.Commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SlashHub extends Command{
	 public SlashHub(){
	        super("hub", "basic",new String[0]);
	 }

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) sender;
            int ran = getHub();
            if(ran == 1){
            	ServerInfo target = ProxyServer.getInstance().getServerInfo("Hub");
            	player.connect(target);
            }
            else if(ran == 2){
            	ServerInfo target = ProxyServer.getInstance().getServerInfo("Hub2");
            	player.connect(target);
            }
            else if(ran == 3){
            	ServerInfo target = ProxyServer.getInstance().getServerInfo("Hub2");
            	player.connect(target);
            }
            else{
            	ServerInfo target = ProxyServer.getInstance().getServerInfo("Hub");
            	player.connect(target);
            }
		}
		else{
			sender.sendMessage(new ComponentBuilder("This command can only be run by a player!").color(ChatColor.AQUA).create());
		}
	}
	public int getHub(){
		int hub;
		int random = (int) Math.floor(Math.random()*3);
		switch(random){
		case 0:
			hub = 1;
		case 1:
			hub = 1;
		case 2:
			hub = 2;
		case 3:
			hub = 3;
		default:
			hub = 3;
		}
		return hub;
	}
}
