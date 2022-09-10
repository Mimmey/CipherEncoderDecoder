package org.mimmey.cryptolab1.cipher.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// Необходимые константы
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Consts {
    public final static int ALPHABET_POWER = 26;
    public final static char ALPHABET_STARTING_CHAR = 'a';
    public final static int RIJNDAEL_PRIME_POLYNOMIAL = 283;

    public final static int[][] RIJNDAEL_MIX_COLUMNS_MATRIX =
            {{2, 3, 1, 1},
                    {1, 2, 3, 1},
                    {1, 1, 2, 3},
                    {3, 1, 1, 2}};

    public final static int[][] RIJNDAEL_SBOX =
            {{2, 3, 1, 1},
                    {1, 2, 3, 1},
                    {1, 1, 2, 3},
                    {3, 1, 1, 2}};

    public final static int[][] RIJNDAEL_RCON = {
            {0x00, 0x00, 0x00, 0x00},
            {0x01, 0x00, 0x00, 0x00},
            {0x02, 0x00, 0x00, 0x00},
            {0x04, 0x00, 0x00, 0x00},
            {0x08, 0x00, 0x00, 0x00},
            {0x10, 0x00, 0x00, 0x00},
            {0x20, 0x00, 0x00, 0x00},
            {0x40, 0x00, 0x00, 0x00},
            {0x80, 0x00, 0x00, 0x00},
            {0x1b, 0x00, 0x00, 0x00},
            {0x36, 0x00, 0x00, 0x00},
    };

    private final static char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static char[] getAlphabet() {
        return ALPHABET.clone();
    }
}
