import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

class Monster {
    int armor;
    int damage;
    int life;
    int speed;
    String name;
    MonsterType monsterType;
    List<Item> itens;
    List<Weapon> weapons;
    List<Armor> armors;

    public Monster(int armour, int damage, int life, int speed, String name) {
        Random r = new Random();
        this.armor = r.nextInt(armour) + 1;
        this.damage = r.nextInt(damage) + 1;
        this.life = r.nextInt(life) + 1;
        this.speed = r.nextInt(speed) + 1;
        this.name = name.isBlank() ? RandomName.get() : name;
        this.itens = new ArrayList<>();
        this.armors = new ArrayList<>();
        this.weapons = new ArrayList<>();
    }

    public int causeDamage(Monster attacker) {
        int totalDamage = attacker.getTotalDamage() - this.getTotalArmour();
        String weapons = attacker.getWeaponsNames();

        if (totalDamage < 0)
            totalDamage = 0;
        if (attacker.life <= 0) {
            System.out.println(attacker.name + " is dead, can not attack!");
            System.out.println(this.name + " life: " + this.life);
        } else if (this.life <= 0) {
            System.out.println(this.name + " is already dead!");
            System.out.println(this.name + " life: " + this.life);
        } else if (this.life > 0 && attacker.life > 0) {
            this.life -= totalDamage;
            System.out.println(attacker.name + " [" + weapons + "] -> " + this.name + ": " + totalDamage);
            System.out.println(this.name + " life: " + this.life);
        }
        return totalDamage;
    }

    int getTotalArmour() {
        int totalArmor = this.armor;
        for (Item item : this.armors) {
            totalArmor += item.getArmor();
        }
        for (Item item : this.weapons) {
            totalArmor += item.getArmor();
        }
        return totalArmor;
    }

    int getTotalDamage() {
        int totalDamage = this.damage;
        for (Item item : this.armors) {
            totalDamage += item.getDamage();
        }
        for (Item item : this.weapons) {
            totalDamage += item.getDamage();
        }
        return totalDamage;
    }

    boolean equip(Item item) {
        boolean isArmor = item.getClass().getName().equals("Armor");
        if (isArmor) {
            for (Armor armor : this.armors) {
                if (((Armor) item).armorType == armor.armorType) {
                    return false;
                }
            }
            this.armors.add((Armor) item);
            return true;
        } else {
            Weapon i = (Weapon) item;
            if ((i.weaponType == WeaponType.Bow || i.weaponType == WeaponType.CrossBow)
                    && this.monsterType != MonsterType.Archer) {
                return false;
            }
            this.weapons.add(i);
            return true;
        }
    }

    String getWeaponsNames() {
        String weapons = "";
        for (Item item : this.itens) {
            boolean isWeapon = item.getClass().getName().equals("Weapon");
            weapons += isWeapon ? (weapons.isEmpty() ? "" : ",") + item.name : "";
        }
        return weapons;
    }

    String getItemsNames() {
        String weapons = "";
        for (Item item : this.itens) {
            weapons += weapons.isEmpty() ? "" : "," + item.name;
        }
        return weapons;
    }

    public JSONObject getJson() {
        JSONObject monster = new JSONObject();
        JSONArray itens = new JSONArray();
        monster.put("name", this.name);
        monster.put("life", this.life);
        monster.put("armour", this.armor);
        monster.put("damage", this.damage);
        monster.put("totalDamage", this.getTotalDamage());
        monster.put("speed", this.speed);
        for (Item item : this.itens) {
            itens.put(item.name);
        }
        monster.put("itens", itens);
        return monster;
    }

    @Override
    public String toString() {
        String text = "Name: " + this.name + " (" + this.life + " + " + this.getTotalArmour() + ")\n";
        text += "Damage: " + this.damage + " (" + this.getTotalDamage() + " | " + this.speed + "x)\n";
        text += "Weapons: " + this.getWeaponsNames();
        return text;
    }
}