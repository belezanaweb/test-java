package br.com.blz.testjava.unit.compose;

import java.util.Random;

public class Utils {

    public static String getPalavraAleatoria() {
        Random random = new Random();
        char[] word = new char[random.nextInt(8) + 3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        return new String(word);
    }

    public static Integer getNumeroAleatorio() {
        return new Random().nextInt();
    }

    public static boolean getRandomTrueOrFalse() {
        return new Random().nextInt() % 2 == 0;
    }
}
