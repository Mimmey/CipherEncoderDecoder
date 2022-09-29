package org.mimmey.cryptolab1.rijndael;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mimmey.cryptolab1.cipher.RijndaelCipher;
import org.mimmey.cryptolab1.cipher.utils.consts.RijndaelConsts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class KeyTransformationsTest {

    @BeforeAll
    public static void setSBoxes() {
        RijndaelCipher.setSBox(RijndaelConsts.SBOX);
        RijndaelCipher.setInvSBox(RijndaelConsts.INV_SBOX);
    }

    @Test
    public void checkGetRconFunc() {
        int[] col = new int[]{0x09, 0xcf, 0x4f, 0x3c};
        int[] rconCol = new int[]{0x01, 0x00, 0x00, 0x00};
        int[] additionalCol = new int[]{0x2b, 0x7e, 0x15, 0x16};

        int[] expected = new int[]{0xa0, 0xfa, 0xfe, 0x17};

        assertArrayEquals(expected, RijndaelCipher.KeyTransformations.getRconFunc(col, rconCol, additionalCol));
    }

    @Test
    public void checkGetKeyExpansion() {
        int[][] array = new int[][]{
                {0x2b, 0x28, 0xab, 0x09},
                {0x7e, 0xae, 0xf7, 0xcf},
                {0x15, 0xd2, 0x15, 0x4f},
                {0x16, 0xa6, 0x88, 0x3c}
        };

        int[][][] expected = new int[][][]{
                {
                        {0x2b, 0x28, 0xab, 0x09},
                        {0x7e, 0xae, 0xf7, 0xcf},
                        {0x15, 0xd2, 0x15, 0x4f},
                        {0x16, 0xa6, 0x88, 0x3c}
                },
                {
                        {0xa0, 0x88, 0x23, 0x2a},
                        {0xfa, 0x54, 0xa3, 0x6c},
                        {0xfe, 0x2c, 0x39, 0x76},
                        {0x17, 0xb1, 0x39, 0x05}
                },
                {
                        {0xf2, 0x7a, 0x59, 0x73},
                        {0xc2, 0x96, 0x35, 0x59},
                        {0x95, 0xb9, 0x80, 0xf6},
                        {0xf2, 0x43, 0x7a, 0x7f}
                }
        };

        int[][][] result = RijndaelCipher.KeyTransformations.getKeyExpansion(array);

        assertArrayEquals(expected[0], result[0]);
        assertArrayEquals(expected[1], result[1]);
    }
}