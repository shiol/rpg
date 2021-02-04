import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

class Monster {
    String name;
    int life;
    int speed;
    int damage;
    int protection;
    MonsterType type;
    List<Item> items;
    List<Weapon> weapons;
    List<Armor> armors;

    public Monster() {
        Random r = new Random();
        this.name = Util.getRandomName();
        this.life = r.nextInt(100) + 1;
        this.speed = r.nextInt(10) + 1;
        this.damage = r.nextInt(10) + 1;
        this.protection = r.nextInt(10) + 1;
        this.items = new ArrayList<>();
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
        int temp = this.protection;
        for (Item item : this.armors) {
            temp += item.getArmor();
        }
        for (Item item : this.weapons) {
            temp += item.getArmor();
        }
        return temp;
    }

    int getTotalArmour(ProtectionType type) {
        int temp = this.protection;
        for (Item item : this.armors) {
            temp += item.getArmor(type);
        }
        for (Item item : this.weapons) {
            temp += item.getArmor(type);
        }
        return temp;
    }

    int getTotalDamage() {
        int temp = this.damage;
        for (Item item : this.armors) {
            temp += item.getDamage();
        }
        for (Item item : this.weapons) {
            temp += item.getDamage();
        }
        return temp;
    }

    int getTotalDamage(DamageType type) {
        int temp = this.damage;
        for (Item item : this.armors) {
            temp += item.getDamage(type);
        }
        for (Item item : this.weapons) {
            temp += item.getDamage(type);
        }
        return temp;
    }

    boolean getItem(Item item) {
        return this.items.add(item);
    }

    boolean equipItem(Item item) {
        boolean isArmor = item.getClass().getName().equals("Armor");
        if (isArmor) {
            Armor a = (Armor) item;
            for (Armor armor : this.armors) {
                if (a.type == armor.type) {
                    return false;
                }
            }
            this.armors.add((Armor) item);
            return true;
        } else {
            Weapon i = (Weapon) item;
            if ((i.type == WeaponType.Bow || i.type == WeaponType.CrossBow)
                    && this.type != MonsterType.Archer) {
                return false;
            } else if ((i.type == WeaponType.HeavyAxe || i.type == WeaponType.HeavySword
                    || i.type == WeaponType.HeavyMace || i.type == WeaponType.HeavyFlail)
                    && this.type != MonsterType.Warrior) {
                return false;
            } else if ((i.type == WeaponType.Staff || i.type == WeaponType.Wand)
                    && this.type != MonsterType.Mage) {
                return false;
            } else if ((i.type == WeaponType.Claws || i.type == WeaponType.Shuriken)
                    && this.type != MonsterType.Ninja) {
                return false;
            }
            this.weapons.add(i);
            return true;
        }
    }

    String getArmorsNames() {
        String items = "";
        for (Item item : this.armors) {
            items += items.isEmpty() ? "" : "," + item.name;
        }
        return items;
    }

    String getWeaponsNames() {
        String items = "";
        for (Item item : this.weapons) {
            items += items.isEmpty() ? "" : "," + item.name;
        }
        return items;
    }

    String getItemsNames() {
        String items = "";
        for (Item item : this.items) {
            items += items.isEmpty() ? "" : "," + item.name;
        }
        return items;
    }

    public JSONObject getJson() {
        JSONObject monster = new JSONObject();
        JSONArray armors = new JSONArray();
        JSONArray weapons = new JSONArray();
        JSONArray itens = new JSONArray();
        monster.put("name", this.name);
        monster.put("life", this.life);
        monster.put("totalProtection", this.getTotalArmour());
        monster.put("totalDamage", this.getTotalDamage());
        monster.put("speed", this.speed);
        for (Item item : this.items) {
            itens.put(item.name);
        }
        for (Item item : this.armors) {
            armors.put(item.name);
        }
        for (Item item : this.weapons) {
            weapons.put(item.name);
        }
        monster.put("itens", itens);
        monster.put("armors", armors);
        monster.put("weapons", weapons);
        return monster;
    }

    @Override
    public String toString() {
        String text = "Name: " + this.name + " (" + this.life + " + " + this.getTotalArmour() + ")\n";
        text += "Damage: " + this.getTotalDamage() + " | " + this.speed + "x\n";
        text += "Weapons: " + this.getWeaponsNames();
        return text;
    }
}