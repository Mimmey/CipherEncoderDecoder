package org.mimmey.cryptolab1.cipher;

import org.mimmey.cryptolab1.cipher.ciphercode.RijndaelKey;
import org.mimmey.cryptolab1.cipher.utils.Consts;

import java.util.List;

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

        public void invSubBytes(byte[][] state, byte[][] sBox) {
        }

        public void invShiftRows(byte[][] state) {
        }

        public void invMixColumns(byte[][] state) {
        }
    }

    static class RijndaelPreparations {
        public List<List<List<Byte>>> splitIntoBlocks(String text) {
            return null;
        }

        public int[][] keyExpansion(byte[][] key) {
            return null;
        }
    }

    static class RindaelOps {

        public int byteSum(int a, int b) {
            return a ^ b;
        }

        public int byteMult(int a, int b) {
            int[] aBits = intToBitsArray(a);
            int[] bBits = intToBitsArray(b);
            int[] result = new int[1000];
            int m = powerOfPolynomial(aBits);
            int n = powerOfPolynomial(bBits);

            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    result[i + j] += aBits[i] * bBits[j];
                }
            }

            for (int k : result) {
                int resultI = 0;

                for (int j = 0; j < Math.abs(k); j++) {
                    resultI = byteSum(resultI, (byte) 1);
                }
            }

            return byteMod(bitsArrayToInt(result), Consts.RIJNDAEL_PRIME_POLYNOMIAL);
        }

        public int byteMod(int dividend, int divisor) {
            int[] dividendBits = intToBitsArray(dividend);
            int[] divisorBits = intToBitsArray(divisor);
            int[] result = new int[32];
            int m = powerOfPolynomial(dividendBits);
            int n = powerOfPolynomial(divisorBits);

            for (int i = m - n; i >= 0; i--) {
                result[i] = dividendBits[i + n] / divisorBits[n];
                for (int j = n; j >= 0; j--) {
                    dividendBits[i + j] -= divisorBits[j] * result[i];
                }
            }

            return bitsArrayToInt(dividendBits);
        }

        private int[] intToBitsArray(int value) {
            int[] bits = new int[Integer.SIZE];
            int twoPower = 1 << Integer.SIZE - 1;

            for (int i = 0; i < Integer.SIZE; i++) {
                bits[i] = (byte) (value / twoPower);
                value %= twoPower;
                twoPower /= 2;
            }

            return bits;
        }

        private int bitsArrayToInt(int[] bits) {
            int result = 0;
            int twoPower = 1 << Integer.SIZE - 1;

            for (int i = 0; i < Integer.SIZE; i++) {
                result += twoPower * bits[i];
                twoPower /= 2;
            }

            return result;
        }

        private int powerOfPolynomial(int[] a) {
            int pos = 0;
            while (a[pos] == 0) {
                pos++;
            }

            return Integer.SIZE - pos;
        }
    }

    static class RijndaelTransformations {

        public void addRoundKey(byte[][] state, byte[][] roundKey) {
        }

        public void subBytes(byte[][] state, byte[][] sBox) {
        }

        public void shiftRows(byte[][] state) {
        }

        public void mixColumns(byte[][] state) {
        }
    }
}
