import java.util.Random;
import java.util.Scanner;

class Main {
    
    public static void main (String args[]) {

        Monster m1 = new Monster(10, 50, 1500, 20);
        Monster m2 = new Monster(10, 50, 1500, 20);
        Scanner s = new Scanner(System.in);
        Random r = new Random();
        Weapon w1 = new Weapon(10);
        Weapon w2 = new Weapon(10);
        m1.itens.add(w1);
        m2.itens.add(w2);

        while (true) {
            System.out.println();
            System.out.println("ACTION:");
            String c = s.next();
            if (c.toLowerCase().equals("a")) {
                m1.causeDamage(m2);
            }
            else if (c.toLowerCase().equals("b")) {
                m2.causeDamage(m1);
            }
            else if (c.toLowerCase().equals("d")) {
                System.out.println("Dice: " + (r.nextInt(10) + 1));
            }
            else if (c.toLowerCase().equals("exit") || c.toLowerCase().equals("quit")) {
                break;
            }
            else {
                System.out.println("Nothing to do!");
            }
        }
        s.close();
    }
}