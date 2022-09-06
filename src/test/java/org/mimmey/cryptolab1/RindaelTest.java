package org.mimmey.cryptolab1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mimmey.cryptolab1.cipher.RijndaelCipher;

public class RindaelTest {
    @Test
    public void checkByteSum() {
        int a = 87;
        int b = 131;

        Assertions.assertEquals(212, new RijndaelCipher.RindaelOps().byteSum(a, b));
    }

    @Test
    public void checkByteMult() {
        int a = 87;
        int b = 131;

        Assertions.assertEquals(193, new RijndaelCipher.RindaelOps().byteMult(a, b));
    }

//    @Test
//    public void checkByteMod() {
//        int a = 87;
//        int b = 131;
//
//        Assertions.assertEquals(193, new RijndaelCipher.RindaelOps().byteMod(a, b));
//    }

    @Test
    public void checkByteToBitsArray() {
        int a = 87;

        Assertions.assertArrayEquals(new int[]{
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 1, 0, 1, 0, 1, 1, 1}, new RijndaelCipher.RindaelOps().intToBitsArray(a));
    }

    @Test
    public void checkBitsArrayToByte() {
        int[] array = new int[]{
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 1, 0, 1, 0, 1, 1, 1};

        Assertions.assertEquals(87, new RijndaelCipher.RindaelOps().bitsArrayToInt(array));
    }
}
