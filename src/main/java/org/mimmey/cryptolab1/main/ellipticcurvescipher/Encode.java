package org.mimmey.cryptolab1.main.ellipticcurvescipher;

import org.mimmey.cryptolab1.cipher.impls.ceasarwithkeyword.paths.CeasarWithKeywordPaths;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.EllipticCurvesCipher;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.cipherkey.EllipticCurvesOpenKey;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.operations.EllipticCurvesLowLevelOperations;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.paths.EllipticCurvesPaths;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Point;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceReader;
import org.mimmey.cryptolab1.cipher.utils.io.ResourceWriter;

import java.util.List;

public class Encode {
    public static void main(String[] args) {
        Point point = Point.toPoint(args[0]);

        List<Integer> randomIntSequence = EllipticCurvesLowLevelOperations.findAllNumbersInString(args[1]);

        String inputPath = args.length > 2 ? args[2] : EllipticCurvesPaths.ENCODE_INPUT.getPath();
        String outputPath = args.length > 3 ? args[3] : EllipticCurvesPaths.ENCODE_OUTPUT.getPath();

        EllipticCurvesCipher cipher = new EllipticCurvesCipher();
        String text = ResourceReader.readFromFile(inputPath);
        ResourceWriter.writeToFile(cipher.encode(text, new EllipticCurvesOpenKey(point, randomIntSequence)), outputPath);
    }
}
