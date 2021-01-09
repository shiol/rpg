class Weapon extends Item {

    WeaponType weaponType;

    public Weapon(int damage, String name) {
        this.damage = damage;
        this.name = name.isBlank() ? RandomName.get() : name;
    }
}