package juice.androidjava.command;

import juice.androidjava.Androidjava;
import juice.androidjava.model.Angry;
import juice.androidjava.utils.Colorize;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.npc.CitizensNPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AngryCommand implements CommandExecutor {

    private Map<UUID, Integer> cooldown = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            return true;
        }

        if (this.cooldown.containsKey(player.getUniqueId())) {
            player.sendMessage(Colorize.color("&cПодождите " + this.cooldown.get(player.getUniqueId())) + " с. перед использованием команды снова!");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("Usage: /angry {nick}");
            return true;
        }

        String name = args[0];
        OfflinePlayer target = Bukkit.getOfflinePlayer(name);

        Location location = player.getLocation();
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name);
        SkinTrait skinTrait = npc.getOrAddTrait(SkinTrait.class);
        skinTrait.setSkinName(name);
        skinTrait.setShouldUpdateSkins(true);
        npc.setProtected(false);

        npc.spawn(location);

        Angry angry = new Angry(name, target.getUniqueId(), npc, Androidjava.getInstance().getAngryTask());
        Androidjava.getInstance().getAngryController().registerAngry(angry);

        angry.startTick();

        this.cooldown.put(player.getUniqueId(), 60);
        startTick(player);

        return true;
    }

    private void startTick(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (cooldown.get(player.getUniqueId()) <= 0) {
                    cooldown.remove(player.getUniqueId());
                    cancel();
                    return;
                }

                cooldown.replace(player.getUniqueId(), cooldown.get(player.getUniqueId())-1);
            }
        }.runTaskTimer(Androidjava.getInstance(), 0L, 20L);
    }
}
