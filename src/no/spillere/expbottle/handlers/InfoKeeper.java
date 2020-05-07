package no.spillere.expbottle.handlers;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import no.spillere.expbottle.ExpBottlePlugin;

public class InfoKeeper {
	
	static FileConfiguration config = ExpBottlePlugin.getPlugin(ExpBottlePlugin.class).getConfig();
	
	// Language
	public static String noPermission = ChatColor.translateAlternateColorCodes('&', config.getString("noPermission"));
	public static String cmdUsageUser = ChatColor.translateAlternateColorCodes('&', config.getString("cmdUsageUser"));
	public static String cmdUsageAdmin = ChatColor.translateAlternateColorCodes('&', config.getString("cmdUsageAdmin"));
	public static String notEnoughXp = ChatColor.translateAlternateColorCodes('&', config.getString("notEnoughXp"));
	public static String xpNotANumber = ChatColor.translateAlternateColorCodes('&', config.getString("xpNotANumber"));
	public static String overMaxUnderMin = ChatColor.translateAlternateColorCodes('&', config.getString("overMaxUnderMin"));
	public static String successfulWithdraw = ChatColor.translateAlternateColorCodes('&', config.getString("successfulWithdraw"));
	public static String reloadSuccessful = ChatColor.translateAlternateColorCodes('&', config.getString("reloadSuccessful"));
	public static String xpBottleGive = ChatColor.translateAlternateColorCodes('&', config.getString("xpBottleGive"));
	public static String xpBottleReceive = ChatColor.translateAlternateColorCodes('&', config.getString("xpBottleReceive"));
	public static String playerNotOnline = ChatColor.translateAlternateColorCodes('&', config.getString("playerNotOnline"));
	public static String giveYourselfXp = ChatColor.translateAlternateColorCodes('&', config.getString("giveYourselfXp"));
	
	// XpBottle
	public static String xpBottleName = ChatColor.translateAlternateColorCodes('&', config.getString("xpBottleName"));
	public static List<String> xpBottleLore = config.getStringList("xpBottleLore");
	
	public static int minXp = config.getInt("minXp");
	public static int maxXp = config.getInt("maxXp");
	
	public static String getInfoKeeper(Player player, String string, int exp, int playerExp) {
		
		string = string.replace("%playername%", player.getName());
		string = string.replace("%playerdisplayname%", player.getDisplayName());
		
		string = string.replace("%xp%", Integer.toString(exp));
		string = string.replace("%minxp%", Integer.toString(minXp));	
		string = string.replace("%maxxp%", Integer.toString(maxXp));
		string = string.replace("%missingxp%", Integer.toString(exp-player.getTotalExperience()));
		string = string.replace("%playerxp%", Integer.toString(player.getTotalExperience()));
		
		return string;
	}
	
	public static String getReceiveInfoKeeper(Player giver, Player receiver, String string, int exp) {
		
		string = string.replace("%playername%", giver.getName());
		string = string.replace("%playerdisplayname%", giver.getDisplayName());
		string = string.replace("%receivername%", receiver.getName());
		string = string.replace("%receiverdisplayname%", receiver.getDisplayName());
		
		string = string.replace("%xp%", Integer.toString(exp));
		string = string.replace("%minxp%", Integer.toString(minXp));	
		string = string.replace("%maxxp%", Integer.toString(maxXp));
		string = string.replace("%playerxp%", Integer.toString(giver.getTotalExperience()));
		
		return string;
	}
}
