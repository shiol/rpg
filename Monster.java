import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        int damage = attacker.damage - this.armour;
        String weapons = "";
        for (Item item : attacker.itens) {
            boolean isWeapon = item.getClass().getName().equals("Weapon");
            damage += isWeapon ? ((Weapon) item).damage : 0;
            weapons += isWeapon ? (weapons.isEmpty() ? "" : ";") + item.name : "";
        }
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
            System.out.println(attacker.name + " [" + weapons + "] -> " + this.name + ": " + damage);
            System.out.println(this.name + " life: " + this.life);
        }
        return damage;
    }
}