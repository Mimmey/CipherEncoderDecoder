package org.mimmey.cryptolab1.cipher.impls.rijndael;

import org.mimmey.cryptolab1.cipher.impls.rijndael.cipherkey.RijndaelKey;
import org.mimmey.cryptolab1.cipher.impls.rijndael.consts.RijndaelConsts;
import org.mimmey.cryptolab1.cipher.impls.rijndael.transformations.InvTransformations;
import org.mimmey.cryptolab1.cipher.impls.rijndael.transformations.KeyTransformations;
import org.mimmey.cryptolab1.cipher.impls.rijndael.transformations.Transformations;
import org.mimmey.cryptolab1.cipher.impls.rijndael.util.operations.RijndaelLowLevelOperations;
import org.mimmey.cryptolab1.cipher.interfaces.Cipher;
import org.mimmey.cryptolab1.cipher.utils.io.BlockLogger;


public abstract class RijndaelCipherMode implements Cipher<RijndaelKey> {

    protected BlockLogger encodeBlockLogger;
    protected BlockLogger decodeBlockLogger;

    protected int[][] iv;
    protected int[][] sBox;
    protected int[][] invSBox;

    public RijndaelCipherMode() {
        this.sBox = RijndaelConsts.SBOX;
        this.invSBox = RijndaelConsts.INV_SBOX;
        this.iv = RijndaelConsts.IV;
    }

    public final int[][] getEncodedBlock(int[][] block, int[][] key, BlockLogger logger) {
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

    public final int[][] getDecodedBlock(int[][] block, int[][] key, BlockLogger logger) {
        int[][][] keySchedule = KeyTransformations.getKeyExpansion(key);

        logger.println("NEW BLOCK");
        logger.println("KeySchedule: ");

        for (int[][] ints : keySchedule) {
            logger.printBlockHex(ints);
        }

        int[][][] reverseKeySchedule = RijndaelLowLevelOperations.reverseArray(keySchedule);

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
}
