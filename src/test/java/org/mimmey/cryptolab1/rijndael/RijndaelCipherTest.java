package org.mimmey.cryptolab1.rijndael;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mimmey.cryptolab1.cipher.impls.rijndael.RijndaelCipherMode;
import org.mimmey.cryptolab1.cipher.impls.rijndael.RijndaelEcbMode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RijndaelCipherTest {

    private static RijndaelCipherMode rijndaelCipherMode;

    @BeforeAll
    public static void setMode() {
        rijndaelCipherMode = new RijndaelEcbMode();
    }

    @Test
    public void checkEncodeBlock() {
        int[][] block = new int[][]{
                {0x32, 0x88, 0x31, 0xe0},
                {0x43, 0x5a, 0x31, 0x37},
                {0xf6, 0x30, 0x98, 0x07},
                {0xa8, 0x8d, 0xa2, 0x34}
        };

        int[][] key = new int[][]{
                {0x2b, 0x28, 0xab, 0x09},
                {0x7e, 0xae, 0xf7, 0xcf},
                {0x15, 0xd2, 0x15, 0x4f},
                {0x16, 0xa6, 0x88, 0x3c}
        };

        int[][] expected = new int[][]{
                {0x39, 0x02, 0xdc, 0x19},
                {0x25, 0xdc, 0x11, 0x6a},
                {0x84, 0x09, 0x85, 0x0b},
                {0x1d, 0xfb, 0x97, 0x32}
        };

        block = rijndaelCipherMode.getEncodedBlock(block, key);

        assertArrayEquals(expected, block);
    }

    @Test
    public void checkDecodeBlock() {
        int[][] block = new int[][]{
                {0x39, 0x02, 0xdc, 0x19},
                {0x25, 0xdc, 0x11, 0x6a},
                {0x84, 0x09, 0x85, 0x0b},
                {0x1d, 0xfb, 0x97, 0x32}
        };

        int[][] key = new int[][]{
                {0x2b, 0x28, 0xab, 0x09},
                {0x7e, 0xae, 0xf7, 0xcf},
                {0x15, 0xd2, 0x15, 0x4f},
                {0x16, 0xa6, 0x88, 0x3c}
        };

        int[][] expected = new int[][]{
                {0x32, 0x88, 0x31, 0xe0},
                {0x43, 0x5a, 0x31, 0x37},
                {0xf6, 0x30, 0x98, 0x07},
                {0xa8, 0x8d, 0xa2, 0x34}
        };

        block = rijndaelCipherMode.getDecodedBlock(block, key);

        assertArrayEquals(expected, block);
    }
}
