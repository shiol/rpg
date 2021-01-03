import java.util.Random;

class Dice {
    static Random r = new Random();
    
    public static int getDice (int max) {
        return r.nextInt(max) + 1;
    }
    
    public static int[] getDice (int max, int quant) {
        int q = (quant == 0 || quant < 0) ? 1 : quant;
        int[] dices = new int[q];
        for (int i = 0; i < dices.length; i++) {
            dices[i] = r.nextInt(max) + 1;
        }
        return dices;
    }

    public static String getDiceText (int max, int quant) {
        int[] dices = getDice(max, quant);
        String text = "[";
        for (int dice : dices) {
            text += (text.length() == 1 ? "" : ",") + dice;
        }
        text += "]";
        return text;
    }
}