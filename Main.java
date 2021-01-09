import java.util.Scanner;

class Main {

    public static void main(String args[]) {

        Monster m1 = new Monster(10, 50, 1500, 20, "");
        Monster m2 = new Monster(10, 50, 1500, 20, "");
        Scanner s = new Scanner(System.in);
        Weapon w1 = new Weapon(10, "");
        Weapon w2 = new Weapon(10, "");
        Weapon w3 = new Weapon(10, "");
        Armor a1 = new Armor(10, "");
        m1.itens.add(w1);
        m2.itens.add(w2);
        m1.itens.add(w3);
        m1.itens.add(a1);

        while (true) {
            System.out.println();
            System.out.println("ACTION:");
            String c = s.next();

            if (c.toLowerCase().equals("a")) {
                m1.causeDamage(m2);
            } else if (c.toLowerCase().equals("b")) {
                m2.causeDamage(m1);
            } else if (c.toLowerCase().equals("show") || c.toLowerCase().equals("desc")
                    || c.toLowerCase().equals("describe")) {
                System.out.println(m1);
                System.out.println();
                System.out.println(m2);
            } else if (c.toLowerCase().equals("j")) {
                System.out.print("Monster 1: ");
                System.out.println(m1.getJson().toString());
                System.out.print("Monster 2: ");
                System.out.println(m2.getJson().toString());
            } else if (c.toLowerCase().equals("d")) {
                System.out.println("Dice: " + Dice.getDice(10));
            } else if (c.toLowerCase().equals("dd")) {
                System.out.println("Dices: " + Dice.getDiceText(10, 4));
            } else if (c.toLowerCase().equals("exit") || c.toLowerCase().equals("quit")) {
                break;
            } else {
                System.out.println("Nothing to do!");
            }
        }
        s.close();
    }
}