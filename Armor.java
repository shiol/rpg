class Armor extends Item {
    ArmorType type;

    public Armor() {
        super();
        Protection basic = new Protection();
        basic.name = "basic";
        this.protections[0] = basic;
    }

    public Armor(ArmorType type) {
        super();
        Protection basic = new Protection();
        basic.name = "basic";
        this.protections[0] = basic;
        this.type = type;
    }
}