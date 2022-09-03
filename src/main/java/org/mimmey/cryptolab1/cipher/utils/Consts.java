package org.mimmey.cryptolab1.cipher.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Consts {

    public final static int ALPHABET_POWER = 26;
    public final static char ALPHABER_STARTING_CHAR = 'a';
    private final static char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static char[] getAlphabet() {
        return ALPHABET.clone();
    }
}
