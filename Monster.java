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
    int itemsCapacity;
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
        this.itemsCapacity = 10;
        this.items = new ArrayList<>();
        this.armors = new ArrayList<>();
        this.weapons = new ArrayList<>();
    }

    public int causeDamage(Monster attacker) {
        int totalDamage = attacker.getTotalDamage() - this.getTotalProtection();
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

    int getTotalProtection() {
        int temp = this.protection;
        for (Item item : this.armors) {
            temp += item.getProtection();
        }
        for (Item item : this.weapons) {
            temp += item.getProtection();
        }
        return temp;
    }

    int getTotalProtection(ActionType type) {
        int temp = this.protection;
        for (Item item : this.armors) {
            temp += item.getProtection(type);
        }
        for (Item item : this.weapons) {
            temp += item.getProtection(type);
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

    int getTotalDamage(ActionType type) {
        int temp = this.damage;
        for (Item item : this.armors) {
            temp += item.getDamage(type);
        }
        for (Item item : this.weapons) {
            temp += item.getDamage(type);
        }
        return temp;
    }

    int getActualCapacity() {
        int temp = 0;
        for (Item item : this.items) {
            temp += item.size;
        }
        return temp;
    }

    boolean getItem(Item item) {
        if (this.itemsCapacity - this.getActualCapacity() >= item.size)
            return this.items.add(item);
        else
            return false;
    }

    boolean isArmor(Item item) {
        return item.getClass().getName().equals("Armor");
    }

    boolean isWeapon(Item item) {
        return item.getClass().getName().equals("Weapon");
    }

    boolean canEquipArmor(Item item) {
        if (!isArmor(item))
            return false;
        Armor a = (Armor) item;
        if (a.typeClass != this.type)
            return false;
        for (Armor armor : this.armors) {
            if (a.type == armor.type) {
                return false;
            }
        }
        return true;
    }

    boolean canEquipWeapon(Item item) {
        if (!isWeapon(item))
            return false;
        Weapon i = (Weapon) item;
        if ((i.type == WeaponType.Bow || i.type == WeaponType.CrossBow) && this.type != MonsterType.Archer) {
            return false;
        } else if ((i.type == WeaponType.HeavyAxe || i.type == WeaponType.HeavySword || i.type == WeaponType.HeavyMace
                || i.type == WeaponType.HeavyFlail) && this.type != MonsterType.Warrior) {
            return false;
        } else if ((i.type == WeaponType.Staff || i.type == WeaponType.Wand) && this.type != MonsterType.Mage) {
            return false;
        } else if ((i.type == WeaponType.Claws || i.type == WeaponType.Shuriken || i.type == WeaponType.Katana)
                && this.type != MonsterType.Ninja) {
            return false;
        }
        return true;
    }

    boolean equipItem(Item item) {
        if (this.items.removeIf(n -> n.id == item.id)) {
            if (this.canEquipArmor(item))
                return this.armors.add((Armor) item);
            else if (this.canEquipWeapon(item))
                return this.weapons.add((Weapon) item);
        }
        return false;
    }

    String getArmorsNames() {
        String items = "";
        for (Item item : this.armors) {
            items += items.isEmpty() ? item.name : "," + item.name;
        }
        return items;
    }

    String getWeaponsNames() {
        String items = "";
        for (Item item : this.weapons) {
            items += items.isEmpty() ? item.name : "," + item.name;
        }
        return items;
    }

    String getItemsNames() {
        String items = "";
        for (Item item : this.items) {
            items += items.isEmpty() ? item.name : "," + item.name;
        }
        return items;
    }

    public JSONObject getJson() {
        JSONObject json = new JSONObject();
        JSONArray armors = new JSONArray();
        JSONArray weapons = new JSONArray();
        JSONArray itens = new JSONArray();
        json.put("name", this.name);
        json.put("life", this.life);
        json.put("totalProtection", this.getTotalProtection());
        json.put("totalDamage", this.getTotalDamage());
        json.put("speed", this.speed);
        for (Item item : this.items) {
            itens.put(item.getJson());
        }
        for (Armor item : this.armors) {
            armors.put(item.getJson());
        }
        for (Weapon item : this.weapons) {
            weapons.put(item.getJson());
        }
        json.put("itens", itens);
        json.put("armors", armors);
        json.put("weapons", weapons);
        return json;
    }

    @Override
    public String toString() {
        String text = "Name: " + this.name + " (" + this.life + " + " + this.getTotalProtection() + ")\n";
        text += "Damage: " + this.getTotalDamage() + " | " + this.speed + "x\n";
        text += "Armors: " + this.getArmorsNames() + "\n";
        text += "Weapons: " + this.getWeaponsNames() + "\n";
        text += "Items: " + this.getItemsNames();
        return text;
    }
}