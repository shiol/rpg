import java.util.Random;

import org.json.JSONObject;

class Damage {
    String name;
    String description;
    int value;
    ActionType type;

    public Damage() {
        this.name = Util.getRandomName();
        this.value = new Random().nextInt(10);
        this.type = ActionType.Fisical;
    }

    public JSONObject getJson(){
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("description", this.description);
        json.put("value", this.value);
        json.put("type", this.type);
        return json;
    }
}