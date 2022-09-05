package org.mimmey.cryptolab1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mimmey.cryptolab1.cipher.CeasarWithKeywordCipher;
import org.mimmey.cryptolab1.cipher.ciphercode.CeasarWithKeywordKey;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceReader;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceWriter;

public class CeasarWithKeywordTest {
    @Test
    public void checkEncode() {

        // Задаем пути, ключи и текст
        String inputPath = "textfiles/ceasar/encode/text.txt";
        String outputPath = "textfiles/ceasar/encode/ciphertext.txt";
        String expectedOutputPath = "textfiles/ceasar/encode/iconicciphertext.txt";

        CeasarWithKeywordCipher cipher = new CeasarWithKeywordCipher();
        int shift = 5;
        String key = "diplomat";

        String expectedResult = ResourceReader.readFileToString(expectedOutputPath);

        // Считываем текст
        String text = ResourceReader.readFileToString(inputPath);

        // Записываем в файл вывода результат
        ResourceWriter.writeToFile(cipher.encode(text, new CeasarWithKeywordKey(shift, key)), outputPath);

        // Получаем этот результат назад из файла вывода
        String result = ResourceReader.readFileToString(outputPath);

        // Проверяем на корректность
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void checkDecode() {

        // Задаем пути, ключи и текст
        String inputPath = "textfiles/ceasar/decode/ciphertext.txt";
        String outputPath = "textfiles/ceasar/decode/text.txt";
        String expectedOutputPath = "textfiles/ceasar/decode/iconictext.txt";

        CeasarWithKeywordCipher cipher = new CeasarWithKeywordCipher();
        int shift = 5;
        String key = "diplomat";

        String expectedResult = ResourceReader.readFileToString(expectedOutputPath);

        // Считываем текст
        String text = ResourceReader.readFileToString(inputPath);

        // Записываем в файл вывода результат
        ResourceWriter.writeToFile(cipher.decode(text, new CeasarWithKeywordKey(shift, key)), outputPath);

        // Получаем этот результат назад из файла вывода
        String result = ResourceReader.readFileToString(outputPath);

        // Проверяем на корректность
        Assertions.assertEquals(expectedResult, result);
    }
}
