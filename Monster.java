import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

class Monster {
    int armour;
    int damage;
    int life;
    int speed;
    String name;
    List<Item> itens;

    public Monster(int armour, int damage, int life, int speed) {
        Random r = new Random();
        this.armour = r.nextInt(armour) + 1;
        this.damage = r.nextInt(damage) + 1;
        this.life = r.nextInt(life) + 1;
        this.speed = r.nextInt(speed) + 1;
        this.name = RandomName.get();
        itens = new ArrayList<Item>();
    }

    public Monster(int armour, int damage, int life, int speed, String name) {
        this.armour = armour;
        this.damage = damage;
        this.life = life;
        this.speed = speed;
        this.name = name;
        itens = new ArrayList<Item>();
    }

    public int causeDamage(Monster attacker) {
        int totalDamage = attacker.getTotalDamage() - this.armour;
        String weapons = attacker.getWeaponsNames();

        if (damage < 0) damage = 0;
        if (attacker.life <= 0) {
            System.out.println(attacker.name + " is dead, can not attack!");
            System.out.println(this.name + " life: " + this.life);
        }
        else if (this.life <= 0) {
            System.out.println(this.name + " is already dead!");
            System.out.println(this.name + " life: " + this.life);
        }
        else if (this.life > 0 && attacker.life > 0) {
            this.life -= damage;
            System.out.println(attacker.name + " [" + weapons + "] -> " + this.name + ": " + totalDamage);
            System.out.println(this.name + " life: " + this.life);
        }
        return damage;
    }

    int getTotalDamage () {
        int totalDamage = this.damage;
        for (Item item : this.itens) {
            boolean isWeapon = item.getClass().getName().equals("Weapon");
            totalDamage += isWeapon ? ((Weapon) item).damage : 0;
        }
        return totalDamage;
    }
    
    String getWeaponsNames () {
        String weapons = "";
        for (Item item : this.itens) {
            boolean isWeapon = item.getClass().getName().equals("Weapon");
            weapons += isWeapon ? (weapons.isEmpty() ? "" : ",") + item.name : "";
        }
        return weapons;
    }

    public JSONObject getJson () {
        JSONObject monster = new JSONObject();
        JSONArray itens = new JSONArray();
        monster.put("name", this.name);
        monster.put("life", this.life);
        monster.put("armour", this.armour);
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
    public String toString () {
        String text = "Name: " + this.name + " (" + this.life + " + " + this.armour + ")\n";
        text += "Damage: " + this.damage + " (" + this.getTotalDamage() + " | " + this.speed + "x)\n";
        text += "Weapons: " + this.getWeaponsNames();
        return text;
    }
}