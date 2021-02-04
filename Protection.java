import java.util.Random;

class Protection {
    String name;
    String description;
    int value;
    ProtectionType type;

    public Protection() {
        this.name = Util.getRandomName();
        this.value = new Random().nextInt(10);
        type = ProtectionType.Fisical;
    }
}