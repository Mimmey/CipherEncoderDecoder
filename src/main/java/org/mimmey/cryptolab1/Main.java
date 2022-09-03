package org.mimmey.cryptolab1;

import org.mimmey.cryptolab1.cipher.CeasarCipher;
import org.mimmey.cryptolab1.cipher.ciphercode.CeasarKey;
import org.mimmey.cryptolab1.cipher.utils.ResourceReader;
import org.mimmey.cryptolab1.cipher.utils.ResourceWriter;

public class Main {
    public static void main(String[] args) {
        String inputPath = "textfiles/text.txt";
        String outputPath = "textfiles/ciphertext.txt";

        CeasarCipher cipher = new CeasarCipher();
        int shift = 5;
        String key = "diplomat";
        
        String text = ResourceReader.readFileToString(inputPath);
        ResourceWriter.writeToFile(cipher.encode(text, new CeasarKey(shift, key)), outputPath);
    }
}
