class Item {
    int armor;
    int damage;
    String name;
    String description;

    /**
     * List of special properties.
     * <p>
     * Even index represent the kind of value:
     * <p>
     * d = damage, a = armour, pd = percentual damage, pa = percentual armor
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

    public int getDamage(int baseDamage) {
        if (special.length == 0) {
            return this.damage;
        } else {
            int temp = this.damage;
            for (int i = 0; i < special.length; i++) {
                if (i % 2 == 0 && special[i].equals("d")) {
                    temp += Integer.parseInt(special[i + 1]);
                } else if (i % 2 == 0 && special[i].equals("pd")) {
                    temp += Double.parseDouble(special[i + 1]) * baseDamage;
                }
            }
            return temp;
        }
    }

    public int getArmor(int baseArmor) {
        if (special.length == 0) {
            return this.armor;
        } else {
            int temp = this.armor;
            for (int i = 0; i < special.length; i++) {
                if (i % 2 == 0 && special[i].equals("a")) {
                    temp += Integer.parseInt(special[i + 1]);
                } else if (i % 2 == 0 && special[i].equals("pa")) {
                    temp += Double.parseDouble(special[i + 1]) * baseArmor;
                }
            }
            return temp;
        }
    }
}