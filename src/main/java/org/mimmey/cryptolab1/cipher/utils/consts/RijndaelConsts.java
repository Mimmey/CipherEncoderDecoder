package org.mimmey.cryptolab1.cipher.utils.consts;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RijndaelConsts {
    public final static int PRIME_POLYNOMIAL = 283;

    public final static int[][] MIX_COLUMNS_MATRIX =
            {{2, 3, 1, 1},
                    {1, 2, 3, 1},
                    {1, 1, 2, 3},
                    {3, 1, 1, 2}};

    public final static int[][] SBOX =
            {{2, 3, 1, 1},
                    {1, 2, 3, 1},
                    {1, 1, 2, 3},
                    {3, 1, 1, 2}};

    public final static int[][] RCON = {
            {0x00, 0x00, 0x00, 0x00},
            {0x01, 0x00, 0x00, 0x00},
            {0x02, 0x00, 0x00, 0x00},
            {0x04, 0x00, 0x00, 0x00},
            {0x08, 0x00, 0x00, 0x00},
            {0x10, 0x00, 0x00, 0x00},
            {0x20, 0x00, 0x00, 0x00},
            {0x40, 0x00, 0x00, 0x00},
            {0x80, 0x00, 0x00, 0x00},
            {0x1b, 0x00, 0x00, 0x00},
            {0x36, 0x00, 0x00, 0x00},
    };

}
