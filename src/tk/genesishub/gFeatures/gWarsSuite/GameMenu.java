package tk.genesishub.gFeatures.gWarsSuite;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

/*
gFeatures
https://github.com/Seshpenguin/gFeatures

   Copyright 2015 DolphinBox

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

//GameMenu System
public class GameMenu {
	Summon summon = new Summon();
	TeamManager tm = new TeamManager();
	Constants cons = new Constants();
	public void Initialize(Player p){
		if(p.getItemInHand().getType() == Material.CHEST && Constants.arena.contains(p.getName())){
			Bukkit.getLogger().info(p.getName());
			InventoryAPI menu = makeInventory(p);
			menu.open(p);
		}
	}
	public InventoryAPI makeInventory(final Player p){
		try{
		InventoryAPI menu = new InventoryAPI("Game Menu", 9, new InventoryAPI.OptionClickEventHandler() {
	        @Override
	        public void onOptionClick(final InventoryAPI.OptionClickEvent event) {
	            if(event.getName().equals(ChatColor.GOLD+"Summon Airplane")){
	            	if(!(Constants.airplaneline.contains(event.getPlayer().getName()))){
	            	summon.Initialize(event.getPlayer(), "airplane");
	            	BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	            	Constants.airplaneline.add(event.getPlayer().getName());
	                scheduler.scheduleSyncDelayedTask(Bukkit.getServer().getPluginManager().getPlugin("gFeatures"), new Runnable() {
	                	public void run(){
	                   cons.removeAirplaneline(event.getPlayer());
	                   }
	                }, 200L);
	            	}
	            	event.getPlayer().closeInventory();
	            }
	            else if(event.getName().equals(ChatColor.GOLD+"Summon Boat")){
		            	if(!(Constants.airplaneline.contains(event.getPlayer().getName()))){
		            	summon.Initialize(event.getPlayer(), "boat");
		            	BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		            	Constants.airplaneline.add(event.getPlayer().getName());
		                scheduler.scheduleSyncDelayedTask(Bukkit.getServer().getPluginManager().getPlugin("gFeatures"), new Runnable() {
		                	public void run(){
		                		cons.removeAirplaneline(event.getPlayer());
		                	}
		                }, 200L);
		            }
		           event.getPlayer().closeInventory();
		      }
	            event.setWillClose(true);
	        }
	    }, Bukkit.getServer().getPluginManager().getPlugin("gFeatures"))
    .setOption(0, new ItemStack(Material.MINECART, 1), ChatColor.GOLD+"Summon Airplane", ChatColor.WHITE+"Stand on an airfield!")
    .setOption(1, new ItemStack(Material.MINECART, 1), ChatColor.GOLD+"Summon Anti Aircraft Guns", ChatColor.WHITE+"Stand close to an Anti-Aircraft Station!")
    .setOption(2, new ItemStack(Material.BOAT, 1), ChatColor.GOLD+"Summon Boat", ChatColor.WHITE+"Stand on a docks!");
	return menu;
	}catch(Exception e){
		e.printStackTrace();
	}
		return null;
    }
}
