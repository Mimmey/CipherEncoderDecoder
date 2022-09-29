package org.mimmey.cryptolab1.cipher.utils.consts;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// Необходимые константы
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CeasarWithKeywordConsts {
    private final static char[] ALPHABET =
            ".,?1234567890абвгдежзиклмнопрстуфхцчшщъыьэюяАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".toCharArray();

    public final static int ALPHABET_POWER = ALPHABET.length;

    public static char[] getAlphabet() {
        return ALPHABET.clone();
    }
}
