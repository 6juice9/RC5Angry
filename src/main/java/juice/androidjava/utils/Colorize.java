package juice.androidjava.utils;

import net.md_5.bungee.api.ChatColor;

public class Colorize {

    public static String color(String input) {
        if (input == null) return input = "";
        return ChatColor.translateAlternateColorCodes('&', input);
    }

}
