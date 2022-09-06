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

    public static class RijndaelInvTransformations {

        public void invSubBytes(byte[][] state, byte[][] sBox) {
        }

        public void invShiftRows(byte[][] state) {
        }

        public void invMixColumns(byte[][] state) {
        }
    }

    public static class RijndaelPreparations {
        public List<List<List<Byte>>> splitIntoBlocks(String text) {
            return null;
        }

        public int[][] keyExpansion(byte[][] key) {
            return null;
        }
    }

    public static class RindaelOps {

        public int byteSum(int a, int b) {
            return a ^ b;
        }

        public int byteMult(int a, int b) {
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

            for (int k : result) {
                int resultI = 0;

                for (int j = 0; j < Math.abs(k); j++) {
                    resultI = byteSum(resultI, (byte) 1);
                }
            }

            return byteModRijndaelPolynomial(bitsArrayToInt(result));
        }

        public int byteModRijndaelPolynomial(int dividend) {
            int[] dividendBits = intToBitsArray(dividend);
            int[] divisorBits = intToBitsArray(Consts.RIJNDAEL_PRIME_POLYNOMIAL);
            int[] result = new int[Integer.SIZE];
            int m = powerOfPolynomial(dividendBits);
            int n = Consts.RIJNDAEL_PRIME_POLYNOMIAL_POWER;

            for (int i = m - n; i >= 0; i--) {
                result[i] = dividendBits[i + n] / divisorBits[n];
                for (int j = n; j >= 0; j--) {
                    dividendBits[Integer.SIZE - m - 1 + i + j]
                            -= divisorBits[Integer.SIZE - n - 1 + j]
                            * result[Integer.SIZE - 1 + i];
                }
            }

            return bitsArrayToInt(dividendBits);
        }

        public int[] intToBitsArray(int value) {
            int[] bits = new int[Integer.SIZE];
            int twoPower = 1 << (Integer.SIZE - 1);
            int i = 0;

            do {
                bits[i] = value / twoPower;
                value %= twoPower;
                twoPower >>= 1;
                twoPower = Math.abs(twoPower);
                i++;
            } while (twoPower > 0);

            return bits;
        }

        public int bitsArrayToInt(int[] bits) {
            int result = 0;
            int twoPower = 1 << (Integer.SIZE - 1);

            for (int bit : bits) {
                result += Math.abs(twoPower * bit);
                twoPower >>= 1;
            }

            return result;
        }

        public int powerOfPolynomial(int[] a) {
            int pos = 0;
            while (a[pos] == 0) {
                pos++;
            }

            return Integer.SIZE - pos - 1;
        }
    }

    public static class RijndaelTransformations {

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
