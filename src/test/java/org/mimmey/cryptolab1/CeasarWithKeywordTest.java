package org.mimmey.cryptolab1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mimmey.cryptolab1.cipher.CeasarWithKeywordCipher;
import org.mimmey.cryptolab1.cipher.cipherkey.CeasarWithKeywordKey;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceReader;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceWriter;

public class CeasarWithKeywordTest {
    @Test
    public void checkEncode() {

        // Задаем пути, ключи и текст
        String inputPath = "src/main/resources/textfiles/ceasarwithkeyword/encode/text.txt";
        String outputPath = "src/main/resources/textfiles/ceasarwithkeyword/encode/ciphertext.txt";
        String expectedOutputPath = "src/main/resources/textfiles/ceasarwithkeyword/encode/iconicciphertext.txt";

        CeasarWithKeywordCipher cipher = new CeasarWithKeywordCipher();
        int shift = 5;
        String key = "ВЫЖИВШИЙ";

        String expectedResult = ResourceReader.readFromFile(expectedOutputPath);

        // Считываем текст
        String text = ResourceReader.readFromFile(inputPath);

        // Записываем в файл вывода результат
        ResourceWriter.writeToFile(cipher.encode(text, new CeasarWithKeywordKey(shift, key)), outputPath);

        // Получаем этот результат назад из файла вывода
        String result = ResourceReader.readFromFile(outputPath);

        // Проверяем на корректность
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void checkDecode() {

        // Задаем пути, ключи и текст
        String inputPath = "src/main/resources/textfiles/ceasarwithkeyword/decode/ciphertext.txt";
        String outputPath = "src/main/resources/textfiles/ceasarwithkeyword/decode/text.txt";
        String expectedOutputPath = "src/main/resources/textfiles/ceasarwithkeyword/decode/iconictext.txt";

        CeasarWithKeywordCipher cipher = new CeasarWithKeywordCipher();
        int shift = 5;
        String key = "ВЫЖИВШИЙ";

        String expectedResult = ResourceReader.readFromFile(expectedOutputPath);

        // Считываем текст
        String text = ResourceReader.readFromFile(inputPath);

        // Записываем в файл вывода результат
        ResourceWriter.writeToFile(cipher.decode(text, new CeasarWithKeywordKey(shift, key)), outputPath);

        // Получаем этот результат назад из файла вывода
        String result = ResourceReader.readFromFile(outputPath);

        // Проверяем на корректность
        Assertions.assertEquals(expectedResult, result);
    }
}
