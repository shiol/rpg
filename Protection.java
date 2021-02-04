import java.util.Random;

import org.json.JSONObject;

class Protection {
    String name;
    String description;
    int value;
    ActionType type;

    public Protection() {
        this.name = Util.getRandomName();
        this.value = new Random().nextInt(10);
        type = ActionType.Fisical;
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