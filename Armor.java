class Armor extends Item {

    ArmorType armorType;

    public Armor(int armor, String name) {
        this.armor = armor;
        this.name = name.isBlank() ? RandomName.get() : name;
    }
}