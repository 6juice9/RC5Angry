package juice.androidjava.model;

import juice.androidjava.Androidjava;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class AngryTask {

    private BukkitTask task;

    public void startAngry(Angry angry) {
        this.task = new BukkitRunnable(){
            int countdown = 15;
            @Override
            public void run() {
                if (countdown <= 0) {
                    handleDelete(angry);
                    task.cancel();
                    return;
                }
                countdown--;
            }
        }.runTaskTimer(Androidjava.getInstance(), 0L, 20L);
    }

    private void handleDelete(Angry angry) {
        CitizensAPI.getNPCRegistry().deregister(angry.getNpc());
        Androidjava.getInstance().getAngryController().removeAngry(angry);
    }

}
