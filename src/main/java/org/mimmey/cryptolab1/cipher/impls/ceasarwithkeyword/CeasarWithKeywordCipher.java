package org.mimmey.cryptolab1.cipher.impls.ceasarwithkeyword;

import lombok.NoArgsConstructor;
import org.mimmey.cryptolab1.cipher.impls.ceasarwithkeyword.cipherkey.CeasarWithKeywordKey;
import org.mimmey.cryptolab1.cipher.impls.ceasarwithkeyword.consts.CeasarWithKeywordConsts;
import org.mimmey.cryptolab1.cipher.interfaces.Cipher;

import java.util.Arrays;

@NoArgsConstructor
public class CeasarWithKeywordCipher implements Cipher<CeasarWithKeywordKey> {

    private char[] getEncodedAlphabet(CeasarWithKeywordKey ceasarWithKeywordKey) {
        boolean[] isUsed = new boolean[CeasarWithKeywordConsts.ALPHABET_POWER];
        Arrays.fill(isUsed, false);
        char[] cipherWord = ceasarWithKeywordKey.getKeyString().toCharArray();
        char[] alphabet = CeasarWithKeywordConsts.getAlphabet();
        char[] encodedAlphabet = new char[CeasarWithKeywordConsts.ALPHABET_POWER];

        int encodedAlphabetIter = ceasarWithKeywordKey.getShift();
        String strAlphabet = new String(alphabet);

        for (char c : cipherWord) {
            if (isUsed[strAlphabet.indexOf(c)]) {
                continue;
            }

            encodedAlphabet[encodedAlphabetIter] = c;
            isUsed[strAlphabet.indexOf(c)] = true;
            encodedAlphabetIter++;
        }

        int startWord = ceasarWithKeywordKey.getShift();
        int endWord = encodedAlphabetIter;

        for (int i = 0; encodedAlphabetIter >= endWord || encodedAlphabetIter < startWord; i++) {
            if (isUsed[i]) {
                continue;
            }

            encodedAlphabet[encodedAlphabetIter] = alphabet[i];
            encodedAlphabetIter = ++encodedAlphabetIter % CeasarWithKeywordConsts.ALPHABET_POWER;
        }

        return encodedAlphabet;
    }

    @Override
    public String encode(String text, CeasarWithKeywordKey cipherKey) {
        String alphabet = String.valueOf(CeasarWithKeywordConsts.getAlphabet());
        String encodedAlphabet = String.valueOf(getEncodedAlphabet(cipherKey));
        char[] cText = text.toCharArray();

        for (int i = 0; i < cText.length; i++) {
            cText[i] = (alphabet.contains(String.valueOf(cText[i])))
                    ? encodedAlphabet.charAt(alphabet.indexOf(cText[i]))
                    : cText[i];
        }

        return String.valueOf(cText);
    }

    @Override
    public String decode(String text, CeasarWithKeywordKey cipherKey) {
        String alphabet = new String(CeasarWithKeywordConsts.getAlphabet());
        String encodedAlphabet = String.valueOf(getEncodedAlphabet(cipherKey));
        char[] cText = text.toCharArray();

        for (int i = 0; i < cText.length; i++) {
            cText[i] = (alphabet.contains(String.valueOf(cText[i])))
                    ? alphabet.charAt(encodedAlphabet.indexOf(cText[i]))
                    : cText[i];
        }

        return String.valueOf(cText);
    }
}
