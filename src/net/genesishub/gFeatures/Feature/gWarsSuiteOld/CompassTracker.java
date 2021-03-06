package net.genesishub.gFeatures.Feature.gWarsSuiteOld;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

public class CompassTracker {
	TeamManager tm = new TeamManager();
	public void set(Player player, Location loc){
		player.setCompassTarget(loc);
	}
	public void onCompassTracker(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if((e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getItemInHand().getType() == Material.COMPASS){
        	ItemStack compass = new ItemStack(Material.COMPASS, 1);
            ItemMeta im1 = compass.getItemMeta();
            im1.setDisplayName(ChatColor.GOLD + "Player Finder <" + getNearest(p, 1500.0).getName() + ">");
            compass.setItemMeta(im1);
            p.setItemInHand(compass);
        	p.setCompassTarget(getNearest(p, 1500.0).getLocation());
        }
    }
	public Player getNearest(Player p, Double range) {
        double distance = Double.POSITIVE_INFINITY; // To make sure the first
                                                    // player checked is closest
        Player target = p;
        for (Entity e : p.getNearbyEntities(range, range, range)) {
            if (!(e instanceof Player))
                continue;
            double distanceto = p.getLocation().distance(e.getLocation());
            if (distanceto > distance)
                continue;
            distance = distanceto;
            if(tm.getTeam(((Player) e).getName()).equals(ChatColor.DARK_AQUA + "blue") && tm.getTeam(p.getName()).equals(ChatColor.GOLD + "orange")){
            	target = (Player) e;
            }
            else if(tm.getTeam(((Player) e).getName()).equals(ChatColor.GOLD + "orange") && tm.getTeam(p.getName()).equals(ChatColor.DARK_AQUA + "blue")){
            	target = (Player) e;
            }
        }
        return target;
    }
}	
