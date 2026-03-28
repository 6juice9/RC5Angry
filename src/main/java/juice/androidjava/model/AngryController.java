package juice.androidjava.model;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class AngryController {

    private Map<String, Angry> angries = new HashMap<>();

    public void registerAngry(Angry angry) {
        if (this.angries.containsKey(angry.getName())) return;
        this.angries.put(angry.getName(), angry);
    }

    public void removeAngry(Angry angry) {
        if (!this.angries.containsKey(angry.getName())) return;
        this.angries.remove(angry.getName(), angry);
    }

    public Map<String, Angry> getAngries() {
        return angries;
    }
}
