package org.mimmey.cryptolab1.cipher;

import lombok.Setter;
import org.mimmey.cryptolab1.cipher.cipherkey.RijndaelKey;
import org.mimmey.cryptolab1.cipher.utils.io.BlockLogger;
import org.mimmey.cryptolab1.cipher.utils.paths.RijndaelPaths;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.lang.Math.abs;
import static org.mimmey.cryptolab1.cipher.utils.consts.RijndaelConsts.*;


public class RijndaelCipher implements Cipher<RijndaelKey> {

    static BlockLogger ofbEncodeLogger;
    static BlockLogger ofbDecodeLogger;
    static BlockLogger cbcEncodeLogger;
    static BlockLogger cbcDecodeLogger;
    static BlockLogger ecbEncodeLogger;
    static BlockLogger ecbDecodeLogger;

    @Setter
    static int[][] iv;
    @Setter
    private static int[][] sBox;
    @Setter
    private static int[][] invSBox;

    static {
        ofbEncodeLogger = new BlockLogger(RijndaelPaths.OFB_ENCODE_LOG.getPath());
        ofbDecodeLogger = new BlockLogger(RijndaelPaths.OFB_DECODE_LOG.getPath());
        cbcEncodeLogger = new BlockLogger(RijndaelPaths.CBC_ENCODE_LOG.getPath());
        cbcDecodeLogger = new BlockLogger(RijndaelPaths.CBC_DECODE_LOG.getPath());
        ecbEncodeLogger = new BlockLogger(RijndaelPaths.ECB_ENCODE_LOG.getPath());
        ecbDecodeLogger = new BlockLogger(RijndaelPaths.ECB_DECODE_LOG.getPath());

        sBox = SBOX;
        invSBox = INV_SBOX;
        iv = IV;
    }

    // Кодируем один блок
    public static int[][] getEncodedBlock(int[][] block, int[][] key, BlockLogger logger) {
        int[][][] keySchedule = KeyTransformations.getKeyExpansion(key);

        logger.println("NEW BLOCK");
        logger.println("KeySchedule: ");

        for (int[][] ints : keySchedule) {
            logger.printBlockHex(ints);
        }

        Transformations.addRoundKey(block, keySchedule[0], logger);

        for (int i = 1; i < 10; i++) {
            block = Transformations.subBytes(block, logger);
            Transformations.shiftRows(block, logger);
            Transformations.mixColumns(block, logger);
            block = Transformations.addRoundKey(block, keySchedule[i], logger);
        }

        block = Transformations.subBytes(block, logger);
        Transformations.shiftRows(block, logger);
        block = Transformations.addRoundKey(block, keySchedule[10], logger);

        return block;
    }

    // Декодируем один блок
    public static int[][] getDecodedBlock(int[][] block, int[][] key, BlockLogger logger) {
        int[][][] keySchedule = KeyTransformations.getKeyExpansion(key);

        logger.println("NEW BLOCK");
        logger.println("KeySchedule: ");

        for (int[][] ints : keySchedule) {
            logger.printBlockHex(ints);
        }

        int[][][] reverseKeySchedule = Ops.reverseArray(keySchedule);

        logger.println("InvKeySchedule: ");

        for (int[][] ints : reverseKeySchedule) {
            logger.printBlockHex(ints);
        }

        block = InvTransformations.addRoundKey(block, reverseKeySchedule[0], logger);
        InvTransformations.shiftRows(block, logger);
        block = InvTransformations.subBytes(block, logger);

        for (int i = 1; i < 10; i++) {
            block = InvTransformations.addRoundKey(block, reverseKeySchedule[i], logger);
            InvTransformations.mixColumns(block, logger);
            InvTransformations.shiftRows(block, logger);
            block = InvTransformations.subBytes(block, logger);
        }

        block = InvTransformations.addRoundKey(block, reverseKeySchedule[10], logger);

        return block;
    }

    // Кодируем текст в режиме OFB
    public String ofbEncode(String text, RijndaelKey cipherKey) {
        int[][] key = KeyTransformations.getKeyFromString(cipherKey.getKey());
        int[][][] dataBlocks = Preparations.getBlocksFromString(text);
        int[][] needsCipher = IV;

        for (int i = 0; i < dataBlocks.length; i++) {
            ofbEncodeLogger.println("Input block: ");
            ofbEncodeLogger.printBlockHex(dataBlocks[i]);

            needsCipher = getEncodedBlock(needsCipher, key, new BlockLogger(RijndaelPaths.OFB_ENCODE_LOG.getPath()));
            dataBlocks[i] = Ops.byteSumMatrices(dataBlocks[i], needsCipher);

            ofbEncodeLogger.println("Output block: ");
            ofbEncodeLogger.printBlockHex(dataBlocks[i]);
        }

        return Preparations.getStringFromBlocks(dataBlocks);
    }

    // Декодируем текст в режиме OFB
    public String ofbDecode(String text, RijndaelKey cipherKey) {
        int[][] key = KeyTransformations.getKeyFromString(cipherKey.getKey());
        int[][][] dataBlocks = Preparations.getBlocksFromString(text);
        int[][] needsEncipher = IV;

        for (int i = 0; i < dataBlocks.length; i++) {
            ofbDecodeLogger.println("Input block: ");
            ofbDecodeLogger.printBlockHex(dataBlocks[i]);

            needsEncipher = getEncodedBlock(needsEncipher, key, new BlockLogger(RijndaelPaths.OFB_DECODE_LOG.getPath()));
            dataBlocks[i] = Ops.byteSumMatrices(dataBlocks[i], needsEncipher);

            ofbDecodeLogger.println("Output block: ");
            ofbDecodeLogger.printBlockHex(dataBlocks[i]);
        }

        return Preparations.getStringFromBlocks(dataBlocks);
    }

    // Кодируем текст в режиме CBC
    public String cbcEncode(String text, RijndaelKey cipherKey) {
        int[][] key = KeyTransformations.getKeyFromString(cipherKey.getKey());
        int[][][] dataBlocks = Preparations.getBlocksFromString(text);
        int[][] needsCipher = IV;

        for (int i = 0; i < dataBlocks.length; i++) {
            cbcEncodeLogger.println("Input block: ");
            cbcEncodeLogger.printBlockHex(dataBlocks[i]);

            dataBlocks[i] = Ops.byteSumMatrices(dataBlocks[i], needsCipher);
            dataBlocks[i] = getEncodedBlock(dataBlocks[i], key, new BlockLogger(RijndaelPaths.CBC_ENCODE_LOG.getPath()));
            needsCipher = dataBlocks[i];

            cbcEncodeLogger.println("Output block: ");
            cbcEncodeLogger.printBlockHex(dataBlocks[i]);
        }

        return Preparations.getStringFromBlocks(dataBlocks);
    }

    // Декодируем текст в режиме CBC
    public String cbcDecode(String text, RijndaelKey cipherKey) {
        int[][] key = KeyTransformations.getKeyFromString(cipherKey.getKey());
        int[][][] dataBlocks = Preparations.getBlocksFromString(text);
        int[][] needsEncipher = IV;

        for (int i = 0; i < dataBlocks.length; i++) {
            cbcDecodeLogger.println("Input block: ");
            cbcDecodeLogger.printBlockHex(dataBlocks[i]);

            int[][] curNeedsEncipher = needsEncipher.clone();
            needsEncipher = dataBlocks[i];
            dataBlocks[i] = getDecodedBlock(dataBlocks[i], key, new BlockLogger(RijndaelPaths.CBC_DECODE_LOG.getPath()));
            dataBlocks[i] = Ops.byteSumMatrices(dataBlocks[i], curNeedsEncipher);

            cbcDecodeLogger.println("Output block: ");
            cbcDecodeLogger.printBlockHex(dataBlocks[i]);
        }

        return Preparations.getStringFromBlocks(dataBlocks);
    }

    // Кодируем текст в режиме ECB
    @Override
    public String encode(String text, RijndaelKey cipherKey) {
        int[][] key = KeyTransformations.getKeyFromString(cipherKey.getKey());
        int[][][] dataBlocks = Preparations.getBlocksFromString(text);

        for (int i = 0; i < dataBlocks.length; i++) {
            ecbEncodeLogger.println("Input block: ");
            ecbEncodeLogger.printBlockHex(dataBlocks[i]);

            dataBlocks[i] = getEncodedBlock(dataBlocks[i], key, new BlockLogger(RijndaelPaths.ECB_ENCODE_LOG.getPath()));

            ecbEncodeLogger.println("Output block: ");
            ecbEncodeLogger.printBlockHex(dataBlocks[i]);
        }

        return Preparations.getStringFromBlocks(dataBlocks);
    }

    // Декодируем текст в режиме ECB
    @Override
    public String decode(String text, RijndaelKey cipherKey) {
        int[][] key = KeyTransformations.getKeyFromString(cipherKey.getKey());
        int[][][] dataBlocks = Preparations.getBlocksFromString(text);

        for (int i = 0; i < dataBlocks.length; i++) {
            ecbDecodeLogger.println("Input block: ");
            ecbDecodeLogger.printBlockHex(dataBlocks[i]);

            dataBlocks[i] = getDecodedBlock(dataBlocks[i], key, new BlockLogger(RijndaelPaths.ECB_DECODE_LOG.getPath()));

            ecbDecodeLogger.println("Output block: ");
            ecbDecodeLogger.printBlockHex(dataBlocks[i]);
        }

        return Preparations.getStringFromBlocks(dataBlocks);
    }

    // Основные обратные преобразования (для декодирования)
    public static class InvTransformations {

        public static int[][] addRoundKey(int[][] state, int[][] roundKey, BlockLogger logger) {
            state = Ops.byteSumMatrices(state, roundKey);

            logger.println("InvAddRoundKey: ");
            logger.printBlockHex(state);

            return state;
        }

        public static int[][] subBytes(int[][] state, BlockLogger logger) {
            state = Ops.invSubBytesMatrix(state);

            logger.println("InvSubBytes: ");
            logger.printBlockHex(state);

            return state;
        }

        public static void shiftRows(int[][] state, BlockLogger logger) {
            Ops.shiftRowRight(state[1], 1);
            Ops.shiftRowRight(state[2], 2);
            Ops.shiftRowRight(state[3], 3);

            logger.println("invShiftRows: ");
            logger.printBlockHex(state);
        }

        public static void mixColumns(int[][] state, BlockLogger logger) {
            for (int i = 0; i < state.length; i++) {
                int[] col = Ops.getColumn(state, i);
                int[] newCol = Ops.multMatrixOnVector(INV_MIX_COLUMNS_MATRIX, col);
                Ops.setColumn(state, newCol, i);
            }

            logger.println("InvMixColumns: ");
            logger.printBlockHex(state);
        }
    }

    // Трансформация ключа
    public static class KeyTransformations {

        // Получения ключа из строки
        public static int[][] getKeyFromString(String strKey) {
            int[] symbols = strKey.chars().toArray();
            return Preparations.splitIntoBlocks(symbols)[0];
        }

        // Получение колонки RCON
        public static int[] getRconFunc(int[] col, int[] rconCol, int[] additionalCol) {
            int[] colCopy = Arrays.copyOf(col, col.length);
            Ops.shiftRowLeft(colCopy, 1);

            colCopy = Ops.subBytesVector(colCopy);
            colCopy = Ops.byteSumVectors(colCopy, rconCol);
            colCopy = Ops.byteSumVectors(colCopy, additionalCol);

            return colCopy;
        }

        // Получение очередного блока расширяемого ключа
        public static int[][] getExpandedBlock(int[][] prevBlock, int roundNumber) {
            int[][] block = new int[4][4];

            Ops.setColumn(block,
                    getRconFunc(Ops.getColumn(prevBlock, 3),
                            RCON[roundNumber],
                            Ops.getColumn(prevBlock, 0)),
                    0);

            for (int i = 1; i < block.length; i++) {
                Ops.setColumn(block,
                        Ops.byteSumVectors(Ops.getColumn(prevBlock, i),
                                Ops.getColumn(block, i - 1)), i);
            }

            return block;
        }

        // Расширение ключа
        public static int[][][] getKeyExpansion(int[][] state) {
            int[][][] keys = new int[11][state.length][state.length];

            keys[0] = Arrays.copyOf(state, state.length);

            for (int i = 1; i < 11; i++) {
                keys[i] = getExpandedBlock(keys[i - 1], i);
            }

            return keys;
        }

    }

    // Вспомогательные операции
    public static class Ops {

        // Инвертировать массив
        public static int[][][] reverseArray(int[][][] array) {
            for (int i = 0; i < array.length / 2; i++) {
                int[][] temp = array[i];
                array[i] = array[array.length - i - 1];
                array[array.length - i - 1] = temp;
            }

            return array;
        }

        // Перевести число в массив битов (10 -> [0, ... 0, 1, 0, 1, 0])
        public static int[] intToBitsArray(int value) {
            int[] bits = new int[BITS_PER_NUMBER_WITH_MARGIN];
            int twoPower = 1 << (BITS_PER_NUMBER_WITH_MARGIN - 1);
            int i = 0;

            do {
                bits[i] = value / twoPower;
                value %= twoPower;
                twoPower >>= 1;
                i++;
            } while (twoPower > 0);

            return bits;
        }

        // Перевести массив битов в число ([0, ... 0, 1, 0, 1, 0] -> 10)
        public static int bitsArrayToInt(int[] bits) {
            int result = 0;
            int twoPower = 1 << (BITS_PER_NUMBER_WITH_MARGIN - 1);

            for (int bit : bits) {
                result += abs(twoPower * bit);
                twoPower >>= 1;
            }

            return result;
        }

        // Найти степень полиномиального (в определениях алгоритма Rijndael) представления массива байтов
        // (10 -> [0, ..., 0, 1, 0, 1, 0] -> x^3 + x^1 -> powerOfPolynomial() == 3)
        public static int powerOfPolynomial(int[] a) {
            if (bitsArrayToInt(a) == 0) {
                return 0;
            }
            int pos = 0;
            while (a[pos] == 0) {
                pos++;
            }

            return BITS_PER_NUMBER_WITH_MARGIN - pos - 1;
        }

        // XOR двух чисел (определение суммы в рамках алгоритма Rijndael)
        public static int byteSum(int a, int b) {
            return a ^ b;
        }

        // Приведение полинома к Рэйнделовской форме
        // (x^4 + 2*x^3 + 5*x^2 + x^1 - 2x^0 -> x^4 + x^2 + x^1, т.к. сумма
        // считается как XOR)
        public static void byteSumForAllBits(int[] a) {
            for (int i = 0; i < a.length; i++) {
                int resultI = 0;

                for (int j = 0; j < abs(a[i]); j++) {
                    resultI = byteSum(resultI, (byte) 1);
                }

                a[i] = resultI;
            }
        }

        // Рэйнделловское суммирование векторов
        public static int[] byteSumVectors(int[] a, int[] b) {
            int[] aCopy = Arrays.copyOf(a, a.length);
            for (int i = 0; i < aCopy.length; i++) {
                aCopy[i] = byteSum(aCopy[i], b[i]);
            }

            return aCopy;
        }

        // Рэйнделловское суммирование матриц
        public static int[][] byteSumMatrices(int[][] a, int[][] b) {
            int[][] aCopy = Arrays.copyOf(a, a.length);

            for (int i = 0; i < aCopy.length; i++) {
                for (int j = 0; j < aCopy[0].length; j++) {
                    aCopy[i][j] = Ops.byteSum(aCopy[i][j], b[i][j]);
                }
            }

            return aCopy;
        }

        // Рэйнделловское умножение (перевести числа в полиномы + умножить как полиномы +
        // привести к Рэйнделловской форме + заменить на остаток от деления Rijndael Prime Polynomial)
        public static int byteMult(int a, int b) {
            int[] aBits = intToBitsArray(a);
            int[] bBits = intToBitsArray(b);
            int[] result = new int[BITS_PER_NUMBER_WITH_MARGIN];
            int m = powerOfPolynomial(aBits);
            int n = powerOfPolynomial(bBits);

            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    result[BITS_PER_NUMBER_WITH_MARGIN - m - n - 1 + i + j]
                            += aBits[BITS_PER_NUMBER_WITH_MARGIN - m - 1 + i]
                            * bBits[BITS_PER_NUMBER_WITH_MARGIN - n - 1 + j];
                }
            }

            byteSumForAllBits(result);
            return byteModRijndaelPolynomial(bitsArrayToInt(result));
        }

        // Рэйнделловское умножение векторов
        public static int multVectors(int[] a, int[] b) {
            int currentSum = 0;

            for (int i = 0; i < a.length; i++) {
                int res = byteMult(a[i], b[i]);
                currentSum = byteSum(currentSum, res);
            }

            return currentSum;
        }

        // Рэйнделловское умножение матрицы на вектор
        public static int[] multMatrixOnVector(int[][] matrix, int[] vector) {
            int[] res = new int[vector.length];

            for (int i = 0; i < res.length; i++) {
                res[i] = multVectors(matrix[i], vector);
            }

            return res;
        }

        // Получить колонку из массива
        public static int[] getColumn(int[][] array, int a) {
            int[] col = new int[array.length];

            for (int i = 0; i < array.length; i++) {
                col[i] = array[i][a];
            }

            return col;
        }

        // Поставить колонку col на место колонки a в массиве
        public static void setColumn(int[][] array, int[] col, int a) {
            for (int i = 0; i < array.length; i++) {
                array[i][a] = col[i];
            }
        }

        // Циклически сдвинуть вектор влево times раз
        public static void shiftRowLeft(int[] row, int times) {
            for (int i = 0; i < times; i++) {
                int temp = row[0];

                for (int j = 0; j < row.length - 1; j++) {
                    row[j] = row[j + 1];
                }

                row[row.length - 1] = temp;
            }
        }

        // Циклически сдвинуть вектор вправо times раз
        public static void shiftRowRight(int[] row, int times) {
            for (int i = 0; i < times; i++) {
                int temp = row[row.length - 1];

                for (int j = row.length - 1; j > 0; j--) {
                    row[j] = row[j - 1];
                }

                row[0] = temp;
            }
        }

        // Найти для вектора массив subBytes
        public static int[] subBytesVector(int[] vec) {
            int[] vecCopy = Arrays.copyOf(vec, vec.length);

            for (int i = 0; i < vecCopy.length; i++) {
                int[] hexIJ = new int[2];
                hexIJ[0] = vecCopy[i] / SBOX_WIDTH;
                hexIJ[1] = vecCopy[i] % SBOX_WIDTH;
                vecCopy[i] = sBox[hexIJ[0]][hexIJ[1]];
            }

            return vecCopy;
        }

        // Найти для вектора массив subBytes по обратному sBox
        public static int[] invSubBytesVector(int[] vec) {
            int[] vecCopy = Arrays.copyOf(vec, vec.length);

            for (int i = 0; i < vecCopy.length; i++) {
                int[] hexIJ = new int[2];
                hexIJ[0] = vecCopy[i] / SBOX_WIDTH;
                hexIJ[1] = vecCopy[i] % SBOX_WIDTH;
                vecCopy[i] = invSBox[hexIJ[0]][hexIJ[1]];
            }

            return vecCopy;
        }

        // Найти для матрицы массив subBytes
        public static int[][] subBytesMatrix(int[][] matrix) {
            int[][] matrixCopy = Arrays.copyOf(matrix, matrix.length);

            for (int i = 0; i < matrixCopy.length; i++) {
                matrixCopy[i] = Ops.subBytesVector(matrixCopy[i]);
            }

            return matrixCopy;
        }

        // Найти для матрицы массив subBytes по обратному sBox
        public static int[][] invSubBytesMatrix(int[][] matrix) {
            int[][] matrixCopy = Arrays.copyOf(matrix, matrix.length);

            for (int i = 0; i < matrixCopy.length; i++) {
                matrixCopy[i] = Ops.invSubBytesVector(matrixCopy[i]);
            }

            return matrixCopy;
        }

        // Найти остаток от деления числа на простой полином Рэйнделла
        private static int byteModRijndaelPolynomial(int dividend) {
            int[] dividendBits = intToBitsArray(dividend);
            int[] divisorBits = intToBitsArray(PRIME_POLYNOMIAL);
            int[] result = new int[BITS_PER_NUMBER_WITH_MARGIN];
            int m = powerOfPolynomial(dividendBits);
            int n = powerOfPolynomial(divisorBits);
            int resIt = BITS_PER_NUMBER_WITH_MARGIN - m + n - 1;

            for (int i = BITS_PER_NUMBER_WITH_MARGIN - m - 1; i < BITS_PER_NUMBER_WITH_MARGIN; i++) {
                if (powerOfPolynomial(dividendBits) < powerOfPolynomial(divisorBits)) {
                    continue;
                }
                result[resIt] = dividendBits[i] / divisorBits[BITS_PER_NUMBER_WITH_MARGIN - n - 1];
                int delta = 0;
                for (int j = BITS_PER_NUMBER_WITH_MARGIN - n - 1; j < BITS_PER_NUMBER_WITH_MARGIN; j++, delta++) {
                    dividendBits[i + delta] -= result[resIt] * divisorBits[j];
                }
                resIt++;
            }

            byteSumForAllBits(dividendBits);
            return bitsArrayToInt(dividendBits);
        }
    }

    // Подготовка к преобразованиям
    public static class Preparations {

        public static int[] byteArrayToIntArray(byte[] bytes) {
            int[] symbols = new int[bytes.length];

            for (int i = 0; i < bytes.length; i++) {
                symbols[i] = bytes[i] & 0xFF;
            }

            return symbols;
        }

        public static byte[] intArrayToByteArray(int[] ints) {
            byte[] bytes = new byte[ints.length];

            for (int i = 0; i < ints.length; i++) {
                bytes[i] = (byte) ints[i];
            }

            return bytes;
        }

        // Разбить строку на массив блоков
        public static int[][][] getBlocksFromString(String text) {
            byte[] bytes = text.getBytes(StandardCharsets.ISO_8859_1);

            return splitIntoBlocks(byteArrayToIntArray(bytes));
        }

        // Соединить массив блоков в строку
        public static String getStringFromBlocks(int[][][] blocks) {
            int[] compressedBlocks = uniteBlocks(blocks);
            byte[] bytes = intArrayToByteArray(compressedBlocks);

            return new String(bytes, StandardCharsets.ISO_8859_1).trim();
        }

        // Разбить одномерный массив чисел на трехмерный
        public static int[][][] splitIntoBlocks(int[] symbols) {
            int addedLength = (BLOCK_CAPACITY - symbols.length % BLOCK_CAPACITY) % BLOCK_CAPACITY;
            int[] symbolsExpansioned = Arrays.copyOf(symbols, symbols.length + addedLength);
            int[][] splittedInto16 = new int[symbolsExpansioned.length / BLOCK_CAPACITY][BLOCK_CAPACITY];

            for (int i = 0; i < symbolsExpansioned.length; i++) {
                splittedInto16[i / BLOCK_CAPACITY][i % BLOCK_CAPACITY] = symbolsExpansioned[i];
            }

            int[][][] res = new int[splittedInto16.length][BLOCK_WIDTH][BLOCK_WIDTH];

            for (int i = 0; i < splittedInto16.length; i++) {
                for (int j = 0; j < splittedInto16[i].length; j++) {
                    res[i][j % BLOCK_WIDTH][j / BLOCK_WIDTH] = splittedInto16[i][j];
                }
            }

            return res;
        }

        // Соединить трехмерный массив блоков в одномерный
        public static int[] uniteBlocks(int[][][] blocks) {
            int[] res = new int[BLOCK_CAPACITY * blocks.length];
            int resIt = 0;

            for (int[][] block : blocks) {
                for (int j = 0; j < block.length; j++) {
                    for (int k = 0; k < block[j].length; k++) {
                        res[resIt] = block[k][j];
                        resIt++;
                    }
                }
            }

            return res;
        }
    }

    // Основные преобразования алгоритма
    public static class Transformations {

        public static int[][] addRoundKey(int[][] state, int[][] roundKey, BlockLogger logger) {
            state = Ops.byteSumMatrices(state, roundKey);

            logger.println("AddRoundKey: ");
            logger.printBlockHex(state);

            return state;
        }

        public static int[][] subBytes(int[][] state, BlockLogger logger) {
            state = Ops.subBytesMatrix(state);

            logger.println("SubBytes: ");
            logger.printBlockHex(state);

            return state;
        }

        public static void shiftRows(int[][] state, BlockLogger logger) {
            Ops.shiftRowLeft(state[1], 1);
            Ops.shiftRowLeft(state[2], 2);
            Ops.shiftRowLeft(state[3], 3);

            logger.println("ShiftRows: ");
            logger.printBlockHex(state);
        }

        public static void mixColumns(int[][] state, BlockLogger logger) {
            for (int i = 0; i < state.length; i++) {
                int[] col = Ops.getColumn(state, i);
                int[] newCol = Ops.multMatrixOnVector(MIX_COLUMNS_MATRIX, col);
                Ops.setColumn(state, newCol, i);
            }

            logger.println("MixColumns: ");
            logger.printBlockHex(state);
        }
    }
}
