package juice.androidjava.model;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;

import java.util.UUID;

public class Angry {

    private final String name;
    private final UUID uuid;
    private final NPC npc;
    private AngryTask angryTask;

    public Angry(String name, UUID uuid, NPC npc, AngryTask angryTask) {
        this.name = name;
        this.uuid = uuid;
        this.npc = npc;
        this.angryTask = angryTask;
    }

    public void startTick() {
        this.angryTask.startAngry(this);
    }

    public String getName() {
        return name;
    }
    public UUID getUuid() {
        return uuid;
    }
    public NPC getNpc() {
        return npc;
    }
}
