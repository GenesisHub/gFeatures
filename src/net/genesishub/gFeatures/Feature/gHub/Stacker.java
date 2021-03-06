package net.genesishub.gFeatures.Feature.gHub;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class Stacker {
	public void event(Player p){
		if(Basis.isInStacker(p.getName())){
			Basis.removeStacker(p.getName());
			p.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "GenesisHub" + ChatColor.GOLD + "] " + ChatColor.RED + "Your stacker is now off!");
		}
		else{
			Basis.addStacker(p.getName());
			p.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "GenesisHub" + ChatColor.GOLD + "] " + ChatColor.GREEN + "Your stacker is now on!");
		}
	}
	  public Player checkPassenger(Player p)
	  {
	    if ((p.getPassenger() != null) && ((p.getPassenger() instanceof Player))) {
	      return (Player)p.getPassenger();
	    }
	    return (Player)p.getPassenger();
	  }
	  
	  public Vector giveVector(Location loc){
	    double pitch = (loc.getPitch() + 90.0F) * 3.141592653589793D / 180.0D;
	    double yaw = (loc.getYaw() + 90.0F) * 3.141592653589793D / 180.0D;
	    
	    double x = Math.sin(pitch) * Math.cos(yaw);
	    double y = Math.sin(pitch) * Math.sin(yaw);
	    double z = Math.cos(pitch);
	    
	    Vector vector = new Vector(x, z, y);
	    
	    return vector;
	  }
	  public void onEntityInteract(PlayerInteractEntityEvent e)
	  {
	    Player p = e.getPlayer();
	    if ((e.getRightClicked() instanceof Player)) {
	        Player pl = (Player)e.getRightClicked();
	          if (Basis.isInStacker(p.getName())) {
	            if (Basis.isInStacker(pl.getName())){
	                p.setPassenger(pl);
	            }
	            else{
	            	p.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "GenesisHub" + ChatColor.GOLD + "] " + ChatColor.GREEN + "The player isn't playing stacker right now!");
	            }
	          }
	        }
	        else{
	          p.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "GenesisHub" + ChatColor.GOLD + "] " + ChatColor.GREEN + "Your stacker needs to be on!");
	        }
	  }
	  public void onInteract(PlayerInteractEvent e)
	  {
	    Player p = e.getPlayer();
	      if ((p.getPassenger() instanceof Player)) {
	          Player pass = (Player)p.getPassenger();
	            pass.leaveVehicle();
	            Location loc = p.getLocation();
	            int strength = 5;
	            if (strength > 0) {
	              pass.setVelocity(giveVector(loc).multiply(strength));
	            } else if (strength < -1) {
	              pass.teleport(loc);
	            }
	      }
	  }
}
