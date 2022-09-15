package org.mimmey.cryptolab1.cipher;

import org.mimmey.cryptolab1.cipher.cipherkey.RijndaelKey;
import org.mimmey.cryptolab1.cipher.utils.consts.RijndaelConsts;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

public class RijndaelCipher implements Cipher<RijndaelKey> {
    @Override
    public String encode(String text, RijndaelKey cipherKey) {
        return null;
    }

    @Override
    public String decode(String text, RijndaelKey cipherKey) {
        return null;
    }

    static class RijndaelInvTransformations {

        public void invSubBytes(int[][] state, int[][] sBox) {
        }

        public void invShiftRows(int[][] state) {
        }

        public void invMixColumns(int[][] state) {
        }
    }

    static class RijndaelPreparations {

        public static int[] getRconFunc(int[] col, int[][] sBox, int[] rconCol, int[] additionalCol) {
            int[] colCopy = Arrays.copyOf(col, col.length);
            RijndaelOps.shiftRow(colCopy);
            RijndaelOps.shiftRow(colCopy);
            RijndaelOps.shiftRow(colCopy);

            colCopy = RijndaelOps.subBytesVector(colCopy, sBox);
            colCopy = RijndaelOps.byteSumVectors(colCopy, rconCol);
            colCopy = RijndaelOps.byteSumVectors(colCopy, additionalCol);

            return colCopy;
        }

        public static int[][][] getKeyExpansion(int[][] state) {
            int[][][] keys = new int[11][state.length][state.length];
            int[][] previous = state;

            keys[0] = Arrays.copyOf(state, state.length);

            for (int i = 1; i < 11; i++) {
                for (int j = 0; j < state.length; j++) {
                    int[] col = RijndaelOps.getColumn(state, j);

                    if (j == state.length - 1) {
                        col = getRconFunc(col, RijndaelConsts.SBOX, RijndaelConsts.RCON[i], RijndaelOps.getColumn(state, 0));
                        previous = keys[i];
                    }

                    col = RijndaelOps.byteSumVectors(col, previous[j]);
                    RijndaelOps.setColumn(state, col, j);
                }
            }

            return keys;
        }

        public List<List<List<Integer>>> splitIntoBlocks(String text) {
            return null;
        }
    }

    public static class RijndaelOps {

        public static int[] intToBitsArray(int value) {
            int[] bits = new int[Integer.SIZE];
            int twoPower = 1 << (Integer.SIZE - 1);
            int i = 0;

            do {
                bits[i] = value / twoPower;
                value %= twoPower;
                twoPower >>= 1;
                twoPower = abs(twoPower);
                i++;
            } while (twoPower > 0);

            return bits;
        }

        public static int bitsArrayToInt(int[] bits) {
            int result = 0;
            int twoPower = 1 << (Integer.SIZE - 1);

            for (int bit : bits) {
                result += abs(twoPower * bit);
                twoPower >>= 1;
            }

            return result;
        }

        public static int powerOfPolynomial(int[] a) {
            int pos = 0;
            while (a[pos] == 0) {
                pos++;
            }

            return Integer.SIZE - pos - 1;
        }

        public static int byteSum(int a, int b) {
            return a ^ b;
        }

        public static void byteSumForAllBits(int[] a) {
            for (int i = 0; i < a.length; i++) {
                int resultI = 0;

                for (int j = 0; j < abs(a[i]); j++) {
                    resultI = byteSum(resultI, (byte) 1);
                }

                a[i] = resultI;
            }
        }

        public static int[] byteSumVectors(int[] a, int[] b) {
            int[] aCopy = Arrays.copyOf(a, a.length);
            for (int i = 0; i < aCopy.length; i++) {
                aCopy[i] = byteSum(aCopy[i], b[i]);
            }

            return aCopy;
        }

        public static int byteModRijndaelPolynomial(int dividend) {
            int[] dividendBits = intToBitsArray(dividend);
            int[] divisorBits = intToBitsArray(RijndaelConsts.PRIME_POLYNOMIAL);
            int[] result = new int[Integer.SIZE];
            int m = powerOfPolynomial(dividendBits);
            int n = powerOfPolynomial(divisorBits);
            int resIt = Integer.SIZE - m + n - 1;

            for (int i = Integer.SIZE - m - 1; i < Integer.SIZE; i++) {
                if (powerOfPolynomial(dividendBits) < powerOfPolynomial(divisorBits)) {
                    continue;
                }
                result[resIt] = dividendBits[i] / divisorBits[Integer.SIZE - n - 1];
                int delta = 0;
                for (int j = Integer.SIZE - n - 1; j < Integer.SIZE; j++, delta++) {
                    dividendBits[i + delta] -= result[resIt] * divisorBits[j];
                }
                resIt++;
            }

            byteSumForAllBits(dividendBits);
            return bitsArrayToInt(dividendBits);
        }

        public static int byteMult(int a, int b) {
            int[] aBits = intToBitsArray(a);
            int[] bBits = intToBitsArray(b);
            int[] result = new int[Integer.SIZE];
            int m = powerOfPolynomial(aBits);
            int n = powerOfPolynomial(bBits);

            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    result[Integer.SIZE - m - n - 1 + i + j] += aBits[Integer.SIZE - m - 1 + i] * bBits[Integer.SIZE - n - 1 + j];
                }
            }

            byteSumForAllBits(result);
            return byteModRijndaelPolynomial(bitsArrayToInt(result));
        }

        public static int multVectors(int[] a, int[] b) {
            int currentSum = 0;

            for (int i = 0; i < a.length; i++) {
                int res = byteMult(a[i], b[i]);
                currentSum = byteSum(currentSum, res);
            }

            return currentSum;
        }

        public static int[] multMatrixOnVector(int[][] matrix, int[] vector) {
            int[] res = new int[vector.length];

            for (int i = 0; i < res.length; i++) {
                res[i] = multVectors(matrix[i], vector);
            }

            return res;
        }

        public static int[] getColumn(int[][] array, int a) {
            int[] col = new int[array.length];
            System.arraycopy(array[a], 0, col, 0, array.length);
            return col;
        }

        public static void setColumn(int[][] array, int[] col, int a) {
            System.arraycopy(col, 0, array[a], 0, array.length);
        }

        public static void shiftRow(int[] row) {
            int temp = row[0];
            row[0] = row[1];
            row[1] = row[2];
            row[2] = row[3];
            row[3] = temp;
        }

        public static int[] subBytesVector(int[] vec, int[][] sBox) {
            int[] vecCopy = Arrays.copyOf(vec, vec.length);

            for (int i = 0; i < vecCopy.length; i++) {
                int[] hexIJ = new int[2];
                hexIJ[0] = vecCopy[i] >> 4;
                hexIJ[1] = vecCopy[i] % (1 << 3);
                vecCopy[i] = sBox[hexIJ[0]][hexIJ[1]];
            }

            return vecCopy;
        }
    }

    static class RijndaelTransformations {

        public void addRoundKey(int[][] state, int[][] roundKey) {
            for (int i = 0; i < state.length; i++) {
                for (int j = 0; j < state[0].length; j++) {
                    state[i][j] = RijndaelOps.byteSum(state[i][j], roundKey[i][j]);
                }
            }
        }

        public void subBytes(int[][] state, int[][] sBox) {
            for (int i = 0; i < state.length; i++) {
                for (int j = 0; j < state[0].length; j++) {
                    int[] hexIJ = new int[2];
                    hexIJ[0] = state[i][j] >> 4;
                    hexIJ[1] = state[i][j] % (1 << 3);
                    state[i][j] = sBox[hexIJ[0]][hexIJ[1]];
                }
            }
        }

        public void shiftRows(int[][] state) {
            RijndaelOps.shiftRow(state[1]);

            RijndaelOps.shiftRow(state[2]);
            RijndaelOps.shiftRow(state[2]);

            RijndaelOps.shiftRow(state[3]);
            RijndaelOps.shiftRow(state[3]);
            RijndaelOps.shiftRow(state[3]);
        }

        public void mixColumns(int[][] state) {
            for (int i = 0; i < state.length; i++) {
                int[] col = RijndaelOps.getColumn(state, i);
                int[] newCol = RijndaelOps.multMatrixOnVector(RijndaelConsts.MIX_COLUMNS_MATRIX, col);
                RijndaelOps.setColumn(state, newCol, i);
            }
        }
    }
}
