package juice.androidjava;

import juice.androidjava.command.AngryCommand;
import juice.androidjava.model.AngryController;
import juice.androidjava.model.AngryTask;
import org.bukkit.plugin.java.JavaPlugin;

public final class Androidjava extends JavaPlugin {

    private static Androidjava instance;
    private AngryController angryController;
    private AngryTask angryTask;

    @Override
    public void onEnable() {
        instance = this;

        angryController = new AngryController();
        angryTask = new AngryTask();

        getCommand("angry").setExecutor(new AngryCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Androidjava getInstance() {
        return instance;
    }
    public AngryController getAngryController() {
        return angryController;
    }
    public AngryTask getAngryTask() {
        return angryTask;
    }
}
