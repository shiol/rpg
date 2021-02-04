import java.util.Scanner;

class Main {

    public static void main(String args[]) {

        Monster m1 = new Monster();
        Monster m2 = new Monster();
        Scanner s = new Scanner(System.in);
        Weapon w1 = new Weapon();
        Weapon w2 = new Weapon();
        Weapon w3 = new Weapon();
        Armor a1 = new Armor();
        m1.items.add(w1);
        m2.items.add(w2);
        m1.items.add(w3);
        m1.items.add(a1);

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
                System.out.println("Dice: " + Util.getDice(10));
            } else if (c.toLowerCase().equals("dd")) {
                System.out.println("Dices: " + Util.getDiceText(10, 4));
            } else if (c.toLowerCase().equals("exit") || c.toLowerCase().equals("quit")) {
                break;
            } else if (c.toLowerCase().equals("n")) {
                System.out.println("Random name: " + Util.getRandomName());
            } else {
                System.out.println("Nothing to do!");
            }
        }
        s.close();
    }
}