package org.mimmey.cryptolab1.main.rijndael.cbc;

import org.mimmey.cryptolab1.cipher.RijndaelCipher;
import org.mimmey.cryptolab1.cipher.cipherkey.RijndaelKey;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceReader;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceWriter;
import org.mimmey.cryptolab1.cipher.utils.paths.RijndaelPaths;

public class Decode {
    public static void main(String[] args) {
        // Принимаем на вход имена входного и выходного файла,
        // ключевое слово
        String key = args.length > 1 ? args[0] : "Secret";

        String inputPath = args.length > 1 ? args[1] : RijndaelPaths.CBC_DECODE_INPUT.getPath();
        String outputPath = args.length > 2 ? args[2] : RijndaelPaths.CBC_DECODE_OUTPUT.getPath();


        RijndaelCipher cipher = new RijndaelCipher();
        String text = ResourceReader.readFromFile(inputPath);
        ResourceWriter.writeToFile(cipher.cbcDecode(text, new RijndaelKey(key)), outputPath);
    }
}