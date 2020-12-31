import java.util.Random;

class Weapon extends Item {
    int damage;
    Random r = new Random();

    public Weapon (int damage) {
        this.damage = r.nextInt(damage);
        this.name = RandomName.get();
    }
    
    public Weapon (int damage, String name) {
        this.damage = damage;
        this.name = name;
    }
}