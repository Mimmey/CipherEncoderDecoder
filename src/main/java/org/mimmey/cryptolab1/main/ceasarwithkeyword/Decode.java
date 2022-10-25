package org.mimmey.cryptolab1.main.ceasarwithkeyword;

import org.mimmey.cryptolab1.cipher.impls.ceasarwithkeyword.CeasarWithKeywordCipher;
import org.mimmey.cryptolab1.cipher.impls.ceasarwithkeyword.cipherkey.CeasarWithKeywordKey;
import org.mimmey.cryptolab1.cipher.impls.ceasarwithkeyword.paths.CeasarWithKeywordPaths;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceReader;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceWriter;

public class Decode {
    public static void main(String[] args) {
        // Принимаем на вход имена входного и выходного файла,
        // смещение и ключевое слово

        // Обязательно меньше длины алфавита
        int shift = Integer.parseInt(args[0]);

        // Обязательно состоит из букв алфавита
        String key = args[1];

        String inputPath = args.length > 2 ? args[2] : CeasarWithKeywordPaths.DECODE_INPUT.getPath();
        String outputPath = args.length > 3 ? args[3] : CeasarWithKeywordPaths.DECODE_OUTPUT.getPath();

        CeasarWithKeywordCipher cipher = new CeasarWithKeywordCipher();
        String text = ResourceReader.readFromFile(inputPath);
        ResourceWriter.writeToFile(cipher.decode(text, new CeasarWithKeywordKey(shift, key)), outputPath);

    }
}
