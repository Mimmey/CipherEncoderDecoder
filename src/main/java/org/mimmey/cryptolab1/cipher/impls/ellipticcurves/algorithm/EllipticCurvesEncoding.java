package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.algorithm;

import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.cipherkey.EllipticCurvesOpenKey;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.operations.EllipticCurvesMathOperations;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Curve;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Point;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.PointPair;

import java.util.ArrayList;
import java.util.List;

public class EllipticCurvesEncoding {

    private final EllipticCurvesMathOperations mathOperations;

    public EllipticCurvesEncoding(Curve curve) {
        this.mathOperations = new EllipticCurvesMathOperations(curve);
    }

    public List<PointPair> getCipherPointPairs(List<Point> points, EllipticCurvesOpenKey key) {
        List<PointPair> cipherPointList = new ArrayList<>();

        assert(key.getRandomNumberList().size() == points.size());
        for (int i = 0; i < key.getRandomNumberList().size(); i++) {
            cipherPointList.add(getCipherPointPair(key.getRandomNumberList().get(i),
                    points.get(i),
                    key.getOpenKey()));
        }

        return cipherPointList;
    }

    private PointPair getCipherPointPair(int randomNumber, Point point, Point openKey) {
        Point firstPoint = getFirstCipherPoint(randomNumber, point);
        Point secondPoint = getSecondCipherPoint(point, randomNumber, openKey);

        return PointPair.of(firstPoint, secondPoint);
    }

    private Point getFirstCipherPoint(int randomNumber, Point generativePoint) {
        return mathOperations.pointSelfMultiplication(generativePoint, randomNumber);
    }

    private Point getSecondCipherPoint(Point point, int randomNumber, Point openKey) {
        return mathOperations.pointPlusPoint(
                point,
                mathOperations.pointSelfMultiplication(openKey, randomNumber)
        );
    }
}
