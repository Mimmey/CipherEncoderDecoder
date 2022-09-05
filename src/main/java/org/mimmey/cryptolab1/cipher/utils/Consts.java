package org.mimmey.cryptolab1.cipher.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// Необходимые константы
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Consts {
    public final static int ALPHABET_POWER = 26;
    public final static char ALPHABER_STARTING_CHAR = 'a';
    public final static int RIJNDAEL_PRIME_POLYNOMIAL = 283;
    private final static char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static char[] getAlphabet() {
        return ALPHABET.clone();
    }
}
