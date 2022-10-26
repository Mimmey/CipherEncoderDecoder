package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.algorithm;

import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.cipherkey.EllipticCurvesClosedKey;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.operations.EllipticCurvesMathOperations;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Curve;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Point;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.PointPair;

import java.util.ArrayList;
import java.util.List;

public class EllipticCurvesDecoding {

    private final EllipticCurvesMathOperations mathOperations;

    public EllipticCurvesDecoding(Curve curve) {
        this.mathOperations = new EllipticCurvesMathOperations(curve);
    }

    public List<Point> getDecodedPoints(List<PointPair> encodedPointPairs, EllipticCurvesClosedKey key) {
        List<Point> encodedPointList = new ArrayList<>();

        for (PointPair pointPair : encodedPointPairs) {
            encodedPointList.add(getDecodedPoint(pointPair, key.getSecretNumber()));
        }

        return encodedPointList;
    }

    private Point getDecodedPoint(PointPair encodedPointPair, int secretNumber) {
        return mathOperations.pointPlusPoint(
                encodedPointPair.getPoint2(),
                mathOperations.pointSelfMultiplication(encodedPointPair.getPoint1(), -1 * secretNumber)
        );
    }
}
