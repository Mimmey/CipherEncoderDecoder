package org.mimmey.cryptolab1.cipher;

import lombok.NoArgsConstructor;
import org.mimmey.cryptolab1.cipher.cipherkey.CeasarWithKeywordKey;
import org.mimmey.cryptolab1.cipher.utils.consts.CeasarWithKeywordConsts;

import java.util.Arrays;

// Реализация алгоритма Цезаря с ключевым словом
@NoArgsConstructor
public class CeasarWithKeywordCipher implements Cipher<CeasarWithKeywordKey> {

    // Метод, возвращающий зашифрованный алфавит (на месте букв алфавита стоят буквы, их шифрующие)
    private char[] getEncodedAlphabet(CeasarWithKeywordKey ceasarWithKeywordKey) {
        boolean[] isUsed = new boolean[CeasarWithKeywordConsts.ALPHABET_POWER];
        Arrays.fill(isUsed, false);
        char[] cipherWord = ceasarWithKeywordKey.getKeyString().toCharArray();
        char[] alphabet = CeasarWithKeywordConsts.getAlphabet();
        char[] encodedAlphabet = new char[CeasarWithKeywordConsts.ALPHABET_POWER];

        int encodedAlphabetIter = ceasarWithKeywordKey.getShift();
        String strAlphabet = new String(alphabet);

        // Вставляем ключевое слово на позицию после сдвига
        for (char c : cipherWord) {
            if (isUsed[strAlphabet.indexOf(c)]) {
                continue;
            }

            encodedAlphabet[encodedAlphabetIter] = c;
            isUsed[strAlphabet.indexOf(c)] = true;
            encodedAlphabetIter++;
        }

        // Обозначаем границы слова внутри массива
        int startWord = ceasarWithKeywordKey.getShift();
        int endWord = encodedAlphabetIter;

        // Заполняем массив остальными буквами алфавита, начиная со следующего после слова символа
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
        char[] encodedAlphabet = getEncodedAlphabet(cipherKey);
        char[] cText = text.toCharArray();

        // Если символ является буквой - шифруем его;
        // Если нет - оставляем как есть
        for (int i = 0; i < cText.length; i++) {
            cText[i] = Character.isLetter(cText[i])
                    ? encodedAlphabet[alphabet.indexOf(cText[i])]
                    : cText[i];
        }

        return String.valueOf(cText);
    }

    @Override
    public String decode(String text, CeasarWithKeywordKey cipherKey) {
        char[] alphabet = CeasarWithKeywordConsts.getAlphabet();
        String encodedAlphabet = String.valueOf(getEncodedAlphabet(cipherKey));
        char[] cText = text.toCharArray();

        // Если символ является буквой - дешифруем его;
        // Если нет - оставляем как есть
        for (int i = 0; i < cText.length; i++) {
            cText[i] = Character.isLetter(cText[i])
                    ? alphabet[encodedAlphabet.indexOf(cText[i])]
                    : cText[i];
        }

        return String.valueOf(cText);
    }
}
