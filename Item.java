class Item {
    String name;
    String description;
    Damage[] damages;
    Protection[] protections;
    int size;
    String id;

    public Item() {
        this.name = Util.getRandomName();
        this.damages = new Damage[10];
        this.protections = new Protection[10];
        this.size = 2;
        this.id = java.util.UUID.randomUUID().toString();
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

    public int getProtection() {
        int temp = 0;
        for (Protection protection : protections) {
            temp += protection.value;
        }
        return temp;
    }

    public int getProtection(ProtectionType type) {
        int temp = 0;
        for (Protection protection : protections) {
            if (protection.type == type) {
                temp += protection.value;
            }
        }
        return temp;
    }
}