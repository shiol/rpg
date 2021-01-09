import java.util.Random;

class RandomName {
    public static String get() {
        char[] vogal = { 97, 101, 105, 111, 117 };
        char[] consoant = { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w',
                'x', 'y', 'z' };
        Random r = new Random();
        boolean first = r.nextBoolean();
        char initial = first ? vogal[r.nextInt(vogal.length)] : consoant[r.nextInt(consoant.length)];
        initial -= 32;
        String name = "" + initial;
        int size = r.nextInt(8) + 1;
        for (int i = 0; i < size; i++) {
            char next = (!first ? i % 2 == 0 : i % 2 != 0) ? vogal[r.nextInt(vogal.length)]
                    : consoant[r.nextInt(consoant.length)];
            name += next;
        }
        return name;
    }
}