package org.mimmey.cryptolab1.cipher.impls.rijndael;

import org.mimmey.cryptolab1.cipher.impls.rijndael.cipherkey.RijndaelKey;
import org.mimmey.cryptolab1.cipher.impls.rijndael.paths.RijndaelPaths;
import org.mimmey.cryptolab1.cipher.impls.rijndael.transformations.KeyTransformations;
import org.mimmey.cryptolab1.cipher.impls.rijndael.util.operations.RijndaelDataBlocksOperations;
import org.mimmey.cryptolab1.cipher.impls.rijndael.util.operations.RijndaelMathOperations;
import org.mimmey.cryptolab1.cipher.utils.io.BlockLogger;

public class RijndaelOfbMode extends RijndaelCipherMode {

    public RijndaelOfbMode() {
        super();
        encodeBlockLogger = new BlockLogger(RijndaelPaths.OFB_ENCODE_LOG.getPath());
        decodeBlockLogger = new BlockLogger(RijndaelPaths.OFB_DECODE_LOG.getPath());
    }

    @Override
    public String encode(String text, RijndaelKey cipherKey) {
        int[][] key = KeyTransformations.getKeyFromString(cipherKey.getKey());
        int[][][] dataBlocks = RijndaelDataBlocksOperations.getBlocksFromString(text);
        int[][] needsCipher = iv;

        for (int i = 0; i < dataBlocks.length; i++) {
            encodeBlockLogger.println("Input block: ");
            encodeBlockLogger.printBlockHex(dataBlocks[i]);

            needsCipher = getEncodedBlock(needsCipher, key, new BlockLogger(RijndaelPaths.OFB_ENCODE_LOG.getPath()));
            dataBlocks[i] = RijndaelMathOperations.byteSumMatrices(dataBlocks[i], needsCipher);

            encodeBlockLogger.println("Output block: ");
            encodeBlockLogger.printBlockHex(dataBlocks[i]);
        }

        return RijndaelDataBlocksOperations.getStringFromBlocks(dataBlocks);
    }

    @Override
    public String decode(String text, RijndaelKey cipherKey) {
        int[][] key = KeyTransformations.getKeyFromString(cipherKey.getKey());
        int[][][] dataBlocks = RijndaelDataBlocksOperations.getBlocksFromString(text);
        int[][] needsEncipher = iv;

        for (int i = 0; i < dataBlocks.length; i++) {
            decodeBlockLogger.println("Input block: ");
            decodeBlockLogger.printBlockHex(dataBlocks[i]);

            needsEncipher = getEncodedBlock(needsEncipher, key, new BlockLogger(RijndaelPaths.OFB_DECODE_LOG.getPath()));
            dataBlocks[i] = RijndaelMathOperations.byteSumMatrices(dataBlocks[i], needsEncipher);

            decodeBlockLogger.println("Output block: ");
            decodeBlockLogger.printBlockHex(dataBlocks[i]);
        }

        return RijndaelDataBlocksOperations.getStringFromBlocks(dataBlocks);
    }
}
