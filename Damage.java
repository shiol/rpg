import java.util.Random;

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
}