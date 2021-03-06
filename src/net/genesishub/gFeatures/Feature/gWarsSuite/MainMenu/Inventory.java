package net.genesishub.gFeatures.Feature.gWarsSuite.MainMenu;

import net.genesishub.gFeatures.API.Messaging.ActionAPI;
import net.genesishub.gFeatures.Feature.gWarsSuite.Statistics;
import net.genesishub.gFeatures.Feature.gWarsSuite.gWarsMode;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Inventory {
	ItemStack item1, item2;
	ActionAPI aapi = new ActionAPI();
	Statistics stats = new Statistics();
	GunMenu gm = new GunMenu();
	public Inventory(){
		ItemStack items = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.CREEPER.ordinal());
		SkullMeta item1meta = (SkullMeta) items.getItemMeta();
		item1meta.setDisplayName(ChatColor.GOLD + "Singleplayer");
		items.setItemMeta(item1meta);
		item1 = items;
		
		item2 = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta item2meta = (SkullMeta) item2.getItemMeta();
		item2meta.setDisplayName(ChatColor.GOLD + "Multiplayer");
		item2.setItemMeta(item2meta);
	}
	public void prevent(InventoryOpenEvent event){
		event.setCancelled(true);
	}
	public void interact(PlayerInteractEvent event){
			if(event.getPlayer().getItemInHand().equals(item1)){
				event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "We are still working on it! Please be patient!");
			}
			else if(event.getPlayer().getItemInHand().equals(item2)){
				aapi.sendActionbar(event.getPlayer(), ChatColor.AQUA + "Please select a team.");
				TeamMenu tm = new TeamMenu();
				stats.setMode(event.getPlayer(), gWarsMode.TEAMMENU);
				tm.initialize(event.getPlayer());
			}
	}
}
