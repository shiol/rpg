class Weapon extends Item {
    WeaponType type;

    public Weapon() {
        super();
        Damage basic = new Damage();
        basic.name = "basic";
        this.damages.add(basic);
    }

    public Weapon(WeaponType type) {
        super();
        Damage basic = new Damage();
        basic.name = "basic";
        this.damages.add(basic);
        this.type = type;
    }
}