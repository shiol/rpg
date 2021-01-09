class Item {
    int armor;
    int damage;
    String name;
    String description;

    /**
     * List of special properties.
     * <p>
     * Even index represent the kind of value: d = damage, a = armour
     * <p>
     * Odd index represent the value.
     * <p>
     * ["d", "10", "a", "5"]
     * <p>
     * Extra damage: 10
     * <p>
     * Extra armour: 5
     **/
    String[] special;

    public int getDamage() {
        if (special.length == 0) {
            return this.damage;
        } else {
            int temp = this.damage;
            for (int i = 0; i < special.length; i++) {
                if (i % 2 == 0 && special[i].equals("d")) {
                    temp += Integer.parseInt(special[i + 1]);
                }
            }
            return temp;
        }
    }

    public int getArmor() {
        if (special.length == 0) {
            return this.armor;
        } else {
            int temp = this.armor;
            for (int i = 0; i < special.length; i++) {
                if (i % 2 == 0 && special[i].equals("a")) {
                    temp += Integer.parseInt(special[i + 1]);
                }
            }
            return temp;
        }
    }
}