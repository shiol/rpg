import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);

        while (true) {
            if (s.nextLine().equals("exit") || s.nextLine().equals("quit"))
                break;
        }
        s.close();
    }
}