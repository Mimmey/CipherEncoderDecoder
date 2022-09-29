package org.mimmey.cryptolab1.main.ceasarwithkeyword;

import org.mimmey.cryptolab1.cipher.CeasarWithKeywordCipher;
import org.mimmey.cryptolab1.cipher.cipherkey.CeasarWithKeywordKey;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceReader;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceWriter;
import org.mimmey.cryptolab1.cipher.utils.paths.CeasarWithKeywordPaths;

public class Encode {
    public static void main(String[] args) {
        // Принимаем на вход имена входного и выходного файла,
        // смещение и ключевое слово

        // Обязательно меньше длины алфавита
        int shift = Integer.parseInt(args[0]);

        // Обязательно состоит из букв алфавита
        String key = args[1];

        String inputPath = args.length > 2 ? args[2] : CeasarWithKeywordPaths.ENCODE_INPUT.getPath();
        String outputPath = args.length > 3 ? args[3] : CeasarWithKeywordPaths.ENCODE_OUTPUT.getPath();

        CeasarWithKeywordCipher cipher = new CeasarWithKeywordCipher();
        String text = ResourceReader.readFromFile(inputPath);
        ResourceWriter.writeToFile(cipher.encode(text, new CeasarWithKeywordKey(shift, key)), outputPath);

    }
}
