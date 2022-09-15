package org.mimmey.cryptolab1;

import org.mimmey.cryptolab1.cipher.CeasarWithKeywordCipher;
import org.mimmey.cryptolab1.cipher.cipherkey.CeasarWithKeywordKey;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceReader;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceWriter;

public class Encode {
    public static void main(String[] args) {
        // Принимаем на вход имена входного и выходного файла,
        // смещение и ключевое слово
        String inputPath = args[0];
        String outputPath = args[1];
        int shift = Integer.parseInt(args[2]);
        String key = args[3];

        CeasarWithKeywordCipher cipher = new CeasarWithKeywordCipher();
        String text = ResourceReader.readFromFile(inputPath);
        ResourceWriter.writeToFile(cipher.encode(text, new CeasarWithKeywordKey(shift, key)), outputPath);

    }
}
