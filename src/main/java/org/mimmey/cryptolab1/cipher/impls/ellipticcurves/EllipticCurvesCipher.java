package org.mimmey.cryptolab1.cipher.impls.ellipticcurves;

import lombok.NoArgsConstructor;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.consts.EllipticCurvesConsts;
import org.mimmey.cryptolab1.cipher.interfaces.Cipher;

import java.util.Arrays;

// Реализация алгоритма Цезаря с ключевым словом
@NoArgsConstructor
public class EllipticCurvesCipher implements Cipher<org.mimmey.cryptolab1.cipher.impls.ellipticcurves.cipherkey.EllipticCurvesKey> {

    // Метод, возвращающий зашифрованный алфавит (на месте букв алфавита стоят буквы, их шифрующие)
    private char[] getEncodedAlphabet(org.mimmey.cryptolab1.cipher.impls.ellipticcurves.cipherkey.EllipticCurvesKey ceasarWithKeywordKey) {
        boolean[] isUsed = new boolean[EllipticCurvesConsts.ALPHABET_POWER];
        Arrays.fill(isUsed, false);
        char[] cipherWord = ceasarWithKeywordKey.getKeyString().toCharArray();
        char[] alphabet = EllipticCurvesConsts.getAlphabet();
        char[] encodedAlphabet = new char[EllipticCurvesConsts.ALPHABET_POWER];

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
            encodedAlphabetIter = ++encodedAlphabetIter % EllipticCurvesConsts.ALPHABET_POWER;
        }

        return encodedAlphabet;
    }

    @Override
    public String encode(String text, org.mimmey.cryptolab1.cipher.impls.ellipticcurves.cipherkey.EllipticCurvesKey cipherKey) {
        String alphabet = String.valueOf(EllipticCurvesConsts.getAlphabet());
        String encodedAlphabet = String.valueOf(getEncodedAlphabet(cipherKey));
        char[] cText = text.toCharArray();

        // Если символ входит в алфавит - шифруем его;
        // Если нет - оставляем как есть
        for (int i = 0; i < cText.length; i++) {
            cText[i] = (alphabet.contains(String.valueOf(cText[i])))
                    ? encodedAlphabet.charAt(alphabet.indexOf(cText[i]))
                    : cText[i];
        }

        return String.valueOf(cText);
    }

    @Override
    public String decode(String text, org.mimmey.cryptolab1.cipher.impls.ellipticcurves.cipherkey.EllipticCurvesKey cipherKey) {
        String alphabet = new String(EllipticCurvesConsts.getAlphabet());
        String encodedAlphabet = String.valueOf(getEncodedAlphabet(cipherKey));
        char[] cText = text.toCharArray();

        // Если символ входит в алфавит - дешифруем его;
        // Если нет - оставляем как есть
        for (int i = 0; i < cText.length; i++) {
            cText[i] = (alphabet.contains(String.valueOf(cText[i])))
                    ? alphabet.charAt(encodedAlphabet.indexOf(cText[i]))
                    : cText[i];
        }

        return String.valueOf(cText);
    }
}
