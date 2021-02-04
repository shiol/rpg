class Item {
    String name;
    String description;
    Damage[] damages;
    Protection[] protections;
    int size;

    public Item() {
        name = Util.getRandomName();
        this.damages = new Damage[10];
        this.protections = new Protection[10];
    }

    public int getDamage() {
        int temp = 0;
        for (Damage damage : damages) {
            temp += damage.value;
        }
        return temp;
    }

    public int getDamage(DamageType type) {
        int temp = 0;
        for (Damage damage : damages) {
            if (damage.type == type) {
                temp += damage.value;
            }
        }
        return temp;
    }

    public int getArmor() {
        int temp = 0;
        for (Protection protection : protections) {
            temp += protection.value;
        }
        return temp;
    }

    public int getArmor(ProtectionType type) {
        int temp = 0;
        for (Protection protection : protections) {
            if (protection.type == type) {
                temp += protection.value;
            }
        }
        return temp;
    }
}