package org.mimmey.cryptolab1.main.rijndael.ecb;

import org.mimmey.cryptolab1.cipher.impls.rijndael.RijndaelCipherMode;
import org.mimmey.cryptolab1.cipher.impls.rijndael.RijndaelEcbMode;
import org.mimmey.cryptolab1.cipher.impls.rijndael.cipherkey.RijndaelKey;
import org.mimmey.cryptolab1.cipher.impls.rijndael.paths.RijndaelPaths;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceReader;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceWriter;

public class Decode {
    public static void main(String[] args) {
        // Принимаем на вход имена входного и выходного файла,
        // ключевое слово
        String key = args.length > 0 ? args[0] : "Secret";

        String inputPath = args.length > 1 ? args[1] : RijndaelPaths.ECB_DECODE_INPUT.getPath();
        String outputPath = args.length > 2 ? args[2] : RijndaelPaths.ECB_DECODE_OUTPUT.getPath();


        RijndaelCipherMode cipher = new RijndaelEcbMode();
        String text = ResourceReader.readFromFile(inputPath);
        ResourceWriter.writeToFile(cipher.decode(text, new RijndaelKey(key)), outputPath);
    }
}
