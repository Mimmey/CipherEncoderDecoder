package org.mimmey.cryptolab1.main.ellipticcurvescipher;

import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.EllipticCurvesCipher;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.cipherkey.EllipticCurvesClosedKey;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.operations.EllipticCurvesLowLevelOperations;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.paths.EllipticCurvesPaths;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.PointPair;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceReader;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceWriter;

import java.util.List;

public class Decode {
    public static void main(String[] args) {
        int secretNumber = Integer.parseInt(args[0]);

        String inputPath = args.length > 1 ? args[1] : EllipticCurvesPaths.DECODE_INPUT.getPath();
        String outputPath = args.length > 2 ? args[2] : EllipticCurvesPaths.DECODE_OUTPUT.getPath();

        EllipticCurvesCipher cipher = new EllipticCurvesCipher();
        String text = ResourceReader.readFromFile(inputPath);
        List<PointPair> decodingUnit = EllipticCurvesLowLevelOperations.pointListStringToPointPairList(text);
        ResourceWriter.writeToFile(cipher.decode(decodingUnit, new EllipticCurvesClosedKey(secretNumber)), outputPath);

    }
}
