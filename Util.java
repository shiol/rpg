import java.util.Random;

class Util {
    static Random r = new Random();

    public static String getRandomName() {
        char[] vogal = { 97, 101, 105, 111, 117 };
        char[] consoant = { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w',
                'x', 'y', 'z' };
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

    public static int getDice(int max) {
        return r.nextInt(max) + 1;
    }

    public static int[] getDice(int max, int quant) {
        int q = (quant == 0 || quant < 0) ? 1 : quant;
        int[] dices = new int[q];
        for (int i = 0; i < dices.length; i++) {
            dices[i] = r.nextInt(max) + 1;
        }
        return dices;
    }

    public static String getDiceText(int max, int quant) {
        int[] dices = getDice(max, quant);
        String text = "[";
        for (int dice : dices) {
            text += (text.length() == 1 ? "" : ",") + dice;
        }
        text += "]";
        return text;
    }
}