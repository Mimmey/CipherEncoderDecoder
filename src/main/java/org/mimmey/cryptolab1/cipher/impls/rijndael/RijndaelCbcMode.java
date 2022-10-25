package org.mimmey.cryptolab1.cipher.impls.rijndael;

import org.mimmey.cryptolab1.cipher.impls.rijndael.cipherkey.RijndaelKey;
import org.mimmey.cryptolab1.cipher.impls.rijndael.paths.RijndaelPaths;
import org.mimmey.cryptolab1.cipher.impls.rijndael.transformations.RijndaelKeyTransformations;
import org.mimmey.cryptolab1.cipher.impls.rijndael.util.operations.RijndaelDataBlocksOperations;
import org.mimmey.cryptolab1.cipher.impls.rijndael.util.operations.RijndaelMathOperations;
import org.mimmey.cryptolab1.cipher.utils.io.BlockLogger;

public class RijndaelCbcMode extends RijndaelCipherMode {

    public RijndaelCbcMode() {
        super();
        encodeBlockLogger = new BlockLogger(RijndaelPaths.CBC_ENCODE_LOG.getPath());
        decodeBlockLogger = new BlockLogger(RijndaelPaths.CBC_DECODE_LOG.getPath());
    }

    @Override
    public String encode(String text, RijndaelKey cipherKey) {
        int[][] key = RijndaelKeyTransformations.getKeyFromString(cipherKey.getKey());
        int[][][] dataBlocks = RijndaelDataBlocksOperations.getBlocksFromString(text);
        int[][] needsCipher = iv;

        for (int i = 0; i < dataBlocks.length; i++) {
            encodeBlockLogger.println("Input block: ");
            encodeBlockLogger.printBlockHex(dataBlocks[i]);

            dataBlocks[i] = RijndaelMathOperations.byteSumMatrices(dataBlocks[i], needsCipher);
            dataBlocks[i] = getEncodedBlock(dataBlocks[i], key);
            needsCipher = dataBlocks[i];

            encodeBlockLogger.println("Output block: ");
            encodeBlockLogger.printBlockHex(dataBlocks[i]);
        }

        return RijndaelDataBlocksOperations.getStringFromBlocks(dataBlocks);
    }

    @Override
    public String decode(String text, RijndaelKey cipherKey) {
        int[][] key = RijndaelKeyTransformations.getKeyFromString(cipherKey.getKey());
        int[][][] dataBlocks = RijndaelDataBlocksOperations.getBlocksFromString(text);
        int[][] needsEncipher = iv;

        for (int i = 0; i < dataBlocks.length; i++) {
            decodeBlockLogger.println("Input block: ");
            decodeBlockLogger.printBlockHex(dataBlocks[i]);

            int[][] curNeedsEncipher = needsEncipher.clone();
            needsEncipher = dataBlocks[i];
            dataBlocks[i] = getDecodedBlock(dataBlocks[i], key);
            dataBlocks[i] = RijndaelMathOperations.byteSumMatrices(dataBlocks[i], curNeedsEncipher);

            decodeBlockLogger.println("Output block: ");
            decodeBlockLogger.printBlockHex(dataBlocks[i]);
        }

        return RijndaelDataBlocksOperations.getStringFromBlocks(dataBlocks);
    }
}
