package org.mimmey.cryptolab1.cipher;

import lombok.NoArgsConstructor;
import org.mimmey.cryptolab1.cipher.ciphercode.CeasarWithKeywordKey;
import org.mimmey.cryptolab1.cipher.utils.Consts;

import java.util.Arrays;

// Реализация алгоритма Цезаря с ключевым словом
@NoArgsConstructor
public class CeasarWithKeywordCipher implements Cipher<CeasarWithKeywordKey> {

    // Метод, возвращающий зашифрованный алфавит (на месте букв алфавита стоят буквы, их шифрующие)
    private char[] getEncodedAlphabet(CeasarWithKeywordKey ceasarWithKeywordKey) {
        boolean[] isUsed = new boolean[Consts.ALPHABET_POWER];
        Arrays.fill(isUsed, false);
        char[] cipherWord = ceasarWithKeywordKey.getKeyString().toCharArray();
        char[] alphabet = Consts.getAlphabet();
        char[] encodedAlphabet = new char[Consts.ALPHABET_POWER];

        int encodedAlphabetIter = ceasarWithKeywordKey.getShift();

        // Вставляем ключевое слово на позицию после сдвига
        for (char c : cipherWord) {
            if (isUsed[c - Consts.ALPHABER_STARTING_CHAR]) {
                continue;
            }

            encodedAlphabet[encodedAlphabetIter] = c;
            isUsed[(c - Consts.ALPHABER_STARTING_CHAR)] = true;
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
            encodedAlphabetIter = ++encodedAlphabetIter % Consts.ALPHABET_POWER;
        }

        return encodedAlphabet;
    }

    @Override
    public String encode(String text, CeasarWithKeywordKey cipherKey) {
        String alphabet = String.valueOf(Consts.getAlphabet());
        char[] encodedAlphabet = getEncodedAlphabet(cipherKey);
        char[] cText = text.toLowerCase().toCharArray();

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
        char[] alphabet = Consts.getAlphabet();
        String encodedAlphabet = String.valueOf(getEncodedAlphabet(cipherKey));
        char[] cText = text.toLowerCase().toCharArray();

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
