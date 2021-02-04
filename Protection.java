import java.util.Random;

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
}