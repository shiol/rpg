class Armor extends Item {
    ArmorType type;
    MonsterType typeClass;

    public Armor() {
        super();
        Protection basic = new Protection();
        basic.name = "basic";
        this.protections.add(basic);
    }

    public Armor(ArmorType type) {
        super();
        Protection basic = new Protection();
        basic.name = "basic";
        this.protections.add(basic);
        this.type = type;
    }
}