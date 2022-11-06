package org.mimmey.cryptolab1.cipher.impls.ellipticcurves;

import lombok.NoArgsConstructor;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.algorithm.EllipticCurvesDecoding;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.algorithm.EllipticCurvesEncoding;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.cipherkey.EllipticCurvesClosedKey;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.cipherkey.EllipticCurvesOpenKey;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.consts.EllipticCurvesConsts;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.operations.EllipticCurvesLowLevelOperations;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Point;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.PointPair;
import org.mimmey.cryptolab1.cipher.interfaces.Cipher;

import java.util.List;

@NoArgsConstructor
public class EllipticCurvesCipher implements Cipher<String, List<PointPair>, EllipticCurvesOpenKey, EllipticCurvesClosedKey> {

    private final EllipticCurvesEncoding encoding = new EllipticCurvesEncoding(EllipticCurvesConsts.curve);
    private final EllipticCurvesDecoding decoding = new EllipticCurvesDecoding(EllipticCurvesConsts.curve);

    @Override
    public String encode(String text, EllipticCurvesOpenKey cipherKey) {
        List<Point> textPoints = EllipticCurvesLowLevelOperations.stringToPointList(text);
        List<PointPair> encodedPointPairs = encoding.getCipherPointPairs(textPoints, cipherKey);

        return EllipticCurvesLowLevelOperations.pointPairListToString(encodedPointPairs);
    }

    @Override
    public String decode(List<PointPair> cipherText, EllipticCurvesClosedKey cipherKey) {
        List<Point> textPoints = decoding.getDecodedPoints(cipherText, cipherKey);
        return EllipticCurvesLowLevelOperations.pointListToString(textPoints);
    }
}
