class Armour extends Item {
    int value;

    public Armour(int value) {
        this.value = value;
        this.name = RandomName.get();
    }

    public Armour(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
