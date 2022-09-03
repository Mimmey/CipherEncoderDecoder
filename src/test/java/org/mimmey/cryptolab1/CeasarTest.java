package org.mimmey.cryptolab1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mimmey.cryptolab1.cipher.CeasarCipher;
import org.mimmey.cryptolab1.cipher.ciphercode.CeasarKey;
import org.mimmey.cryptolab1.cipher.utils.ResourceReader;
import org.mimmey.cryptolab1.cipher.utils.ResourceWriter;

public class CeasarTest {
    @Test
    public void check1() {
        String inputPath = "textfiles/text.txt";
        String outputPath = "textfiles/ciphertext.txt";
        String expectedOutputPath = "textfiles/iconicciphertext.txt";

        CeasarCipher cipher = new CeasarCipher();
        int shift = 5;
        String key = "diplomat";

        String expectedResult = ResourceReader.readFileToString(expectedOutputPath);

        String text = ResourceReader.readFileToString(inputPath);
        ResourceWriter.writeToFile(cipher.encode(text, new CeasarKey(shift, key)), outputPath);
        String result = ResourceReader.readFileToString(outputPath);

        Assertions.assertEquals(expectedResult, result);
    }
}
