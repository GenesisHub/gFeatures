package net.genesishub.gFeatures.Command;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

public class RegisterCommand {
	private static CommandMap cmap;
	public void register(CommandExecutor executor, String cmdname){
	 try{
         if(Bukkit.getServer() instanceof CraftServer){
             final Field f = CraftServer.class.getDeclaredField("commandMap");
             f.setAccessible(true);
             cmap = (CommandMap)f.get(Bukkit.getServer());
         }
     } catch (Exception e){
         e.printStackTrace();
     }
	 gCommand gc = new gCommand(cmdname);
     cmap.register("", gc);
     gc.setExecutor(executor);
	}
}
