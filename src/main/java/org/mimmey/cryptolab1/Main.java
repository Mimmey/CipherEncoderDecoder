package org.mimmey.cryptolab1;

import org.mimmey.cryptolab1.cipher.CeasarWithKeywordCipher;
import org.mimmey.cryptolab1.cipher.ciphercode.CeasarWithKeywordKey;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceReader;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceWriter;

public class Main {
    public static void main(String[] args) {
        String inputPath = "textfiles/ceasar/encode/text.txt";
        String outputPath = "textfiles/ceasar/encode/ciphertext.txt";

        CeasarWithKeywordCipher cipher = new CeasarWithKeywordCipher();
        int shift = 5;
        String key = "diplomat";

        String text = ResourceReader.readFileToString(inputPath);
        ResourceWriter.writeToFile(cipher.encode(text, new CeasarWithKeywordKey(shift, key)), outputPath);
    }
}
