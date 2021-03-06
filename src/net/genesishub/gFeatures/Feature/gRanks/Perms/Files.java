package net.genesishub.gFeatures.Feature.gRanks.Perms;

import net.genesishub.gFeatures.Configuration.Config;
import net.genesishub.gFeatures.Feature.gRanks.Basis;
import net.genesishub.gFeatures.Feature.gRanks.Rank;
import net.genesishub.gFeatures.Feature.gRanks.Retrieve;

public class Files {
	Retrieve r = new Retrieve();
	PermApp pa = new PermApp();
	public void setupFiles(){
		Config c = new Config();
		c.createDirectory("plugins/gFeatures/gRanks/perms", "Created Permissions folder!");
		c.createDirectory("plugins/gFeatures/gRanks/inherit", "Created Inherit folder!");
		c.createDirectory("plugins/gFeatures/gRanks/ginherit", "Created Global Inherit folder!");
		c.createDirectory("plugins/gFeatures/gRanks/gperms", "Created Global Permissions folder!");
		for(Rank r : Basis.getRanks()){
			c.createFile("plugins/gFeatures/gRanks/perms/" + r.getName() + ".txt", "Created permissions file for " + r.getName() + "!");
			c.createFile("plugins/gFeatures/gRanks/gperms/" + r.getName() + ".txt", "Created global permissions file for " + r.getName() + "!");
			c.createFile("plugins/gFeatures/gRanks/inherit/" + r.getName() + ".txt", "Created inheritance file for " + r.getName() + "!");
			c.createFile("plugins/gFeatures/gRanks/ginherit/" + r.getName() + ".txt", "Created global inheritance file for " + r.getName() + "!");
		}
		pa.setupPerms();
	}
}