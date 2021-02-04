import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

class Item {
    String name;
    String description;
    List<Damage> damages;
    List<Protection> protections;
    int size;
    String id;

    public Item() {
        this.name = Util.getRandomName();
        this.damages = new ArrayList<>();
        this.protections = new ArrayList<>();
        this.size = 2;
        this.id = java.util.UUID.randomUUID().toString();
    }

    public int getDamage() {
        int temp = 0;
        for (Damage damage : damages) {
            if (damage != null)
                temp += damage.value;
        }
        return temp;
    }

    public int getDamage(ActionType type) {
        int temp = 0;
        for (Damage damage : damages) {
            if (damage != null && damage.type == type) {
                temp += damage.value;
            }
        }
        return temp;
    }

    public int getProtection() {
        int temp = 0;
        for (Protection protection : protections) {
            if (protection != null)
                temp += protection.value;
        }
        return temp;
    }

    public int getProtection(ActionType type) {
        int temp = 0;
        for (Protection protection : protections) {
            if (protection != null && protection.type == type) {
                temp += protection.value;
            }
        }
        return temp;
    }

    public JSONObject getJson() {
        JSONObject json = new JSONObject();
        JSONArray damages = new JSONArray();
        JSONArray protections = new JSONArray();
        json.put("id", this.id);
        json.put("name", this.name);
        json.put("description", this.description);
        json.put("size", this.size);
        json.put("damage", this.getDamage());
        json.put("protection", this.getProtection());
        for (Damage item : this.damages) {
            damages.put(item.getJson());
        }
        for (Protection item : this.protections) {
            protections.put(item.getJson());
        }
        json.put("damageProperties", damages);
        json.put("protectionProperties", protections);
        return json;
    }
}