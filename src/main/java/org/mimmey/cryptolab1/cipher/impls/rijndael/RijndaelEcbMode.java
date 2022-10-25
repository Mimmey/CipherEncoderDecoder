package org.mimmey.cryptolab1.cipher.impls.rijndael;

import org.mimmey.cryptolab1.cipher.impls.rijndael.cipherkey.RijndaelKey;
import org.mimmey.cryptolab1.cipher.impls.rijndael.paths.RijndaelPaths;
import org.mimmey.cryptolab1.cipher.impls.rijndael.transformations.RijndaelKeyTransformations;
import org.mimmey.cryptolab1.cipher.impls.rijndael.util.operations.RijndaelDataBlocksOperations;
import org.mimmey.cryptolab1.cipher.utils.io.BlockLogger;

public class RijndaelEcbMode extends RijndaelCipherMode {

    public RijndaelEcbMode() {
        super();
        encodeBlockLogger = new BlockLogger(RijndaelPaths.ECB_ENCODE_LOG.getPath());
        decodeBlockLogger = new BlockLogger(RijndaelPaths.ECB_DECODE_LOG.getPath());
    }

    @Override
    public String encode(String text, RijndaelKey cipherKey) {
        int[][] key = RijndaelKeyTransformations.getKeyFromString(cipherKey.getKey());
        int[][][] dataBlocks = RijndaelDataBlocksOperations.getBlocksFromString(text);

        for (int i = 0; i < dataBlocks.length; i++) {
            encodeBlockLogger.println("Input block: ");
            encodeBlockLogger.printBlockHex(dataBlocks[i]);

            dataBlocks[i] = getEncodedBlock(dataBlocks[i], key);

            encodeBlockLogger.println("Output block: ");
            encodeBlockLogger.printBlockHex(dataBlocks[i]);
        }

        return RijndaelDataBlocksOperations.getStringFromBlocks(dataBlocks);
    }

    @Override
    public String decode(String text, RijndaelKey cipherKey) {
        int[][] key = RijndaelKeyTransformations.getKeyFromString(cipherKey.getKey());
        int[][][] dataBlocks = RijndaelDataBlocksOperations.getBlocksFromString(text);

        for (int i = 0; i < dataBlocks.length; i++) {
            decodeBlockLogger.println("Input block: ");
            decodeBlockLogger.printBlockHex(dataBlocks[i]);

            dataBlocks[i] = getDecodedBlock(dataBlocks[i], key);

            decodeBlockLogger.println("Output block: ");
            decodeBlockLogger.printBlockHex(dataBlocks[i]);
        }

        return RijndaelDataBlocksOperations.getStringFromBlocks(dataBlocks);
    }
}
