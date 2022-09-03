package org.mimmey.cryptolab1.cipher;

import lombok.NoArgsConstructor;
import org.mimmey.cryptolab1.cipher.ciphercode.CeasarKey;
import org.mimmey.cryptolab1.cipher.utils.Consts;

import java.util.Arrays;

@NoArgsConstructor
public class CeasarCipher implements Cipher<CeasarKey> {

    private char[] getEncodedAlphabet(CeasarKey ceasarKey) {
        boolean[] isUsed = new boolean[Consts.ALPHABET_POWER];
        Arrays.fill(isUsed, false);
        char[] cipherWord = ceasarKey.getKeyString().toCharArray();
        char[] alphabet = Consts.getAlphabet();
        char[] encodedAlphabet = new char[Consts.ALPHABET_POWER];

        int encodedAlphabetIter = ceasarKey.getShift();

        for (char c : cipherWord) {
            if (isUsed[c - Consts.ALPHABER_STARTING_CHAR]) {
                continue;
            }

            encodedAlphabet[encodedAlphabetIter] = c;
            isUsed[(c - Consts.ALPHABER_STARTING_CHAR)] = true;
            encodedAlphabetIter++;
        }

        int startWord = ceasarKey.getShift();
        int endWord = encodedAlphabetIter;

        for (int i = 0; encodedAlphabetIter >= endWord || encodedAlphabetIter < startWord; i++) {
            if (isUsed[i]) {
                continue;
            }

            encodedAlphabet[encodedAlphabetIter] = alphabet[i];
            encodedAlphabetIter = ++encodedAlphabetIter % Consts.ALPHABET_POWER;
        }

        return encodedAlphabet;
    }

    @Override
    public String encode(String text, CeasarKey cipherKey) {
        String alphabet = String.valueOf(Consts.getAlphabet());
        char[] encodedAlphabet = getEncodedAlphabet(cipherKey);
        char[] cText = text.toLowerCase().toCharArray();

        for (int i = 0; i < cText.length; i++) {
            cText[i] = Character.isLetter(cText[i])
                    ? encodedAlphabet[alphabet.indexOf(cText[i])]
                    : cText[i];
        }

        return String.valueOf(cText);
    }

    @Override
    public String decode(String text, CeasarKey cipherKey) {
        return null;
    }
}
