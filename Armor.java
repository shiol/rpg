class Armor extends Item {
    int value;
    
    public Armor(int value, String name) {
        this.value = value;
        this.name = name.isEmpty() ? RandomName.get() : name;
    }
}