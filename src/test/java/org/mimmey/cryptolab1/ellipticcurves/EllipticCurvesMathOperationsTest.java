package org.mimmey.cryptolab1.ellipticcurves;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.consts.EllipticCurvesConsts;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.operations.EllipticCurvesMathOperations;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Curve;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EllipticCurvesMathOperationsTest {

    private static EllipticCurvesMathOperations mathOperations;

    @BeforeAll
    public static void setMathOperations() {
        mathOperations = new EllipticCurvesMathOperations(EllipticCurvesConsts.curve);
    }

    @Test
    public void checkPointSelfMultiplication() {
        Point point = Point.of(0, 1);

        Point found = mathOperations.pointSelfMultiplication(point, 3);

        Point expected = Point.of(56, 419);
        assertEquals(expected, found);
    }

    @Test
    public void checkPointSelfMultiplicationNegative() {
        Point point = Point.of(56, 419);

        Point found = mathOperations.pointSelfMultiplication(point, -45);

        Point expected = Point.of(175, 559);
        assertEquals(expected, found);
    }

    @Test
    public void checkPointPlusSelf() {
        Point point = Point.of(0, 1);

        Point found = mathOperations.pointPlusSelf(point);

        Point expected = Point.of(188, 93);
        assertEquals(expected, found);
    }

    @Test
    public void checkPointPlusPoint() {
        Point point1 = Point.of(301, 734);
        Point point2 = Point.of(175, 559);

        Point found = mathOperations.pointPlusPoint(point1, point2);

        Point expected = Point.of(66, 552);
        assertEquals(expected, found);
    }

    @Test
    public void checkStatement() {
        Point point1 = Point.of(66, 552);
        Point point2 = Point.of(406, 397);

        Point kPb = mathOperations.pointSelfMultiplication(point2, 3);

        Point found = mathOperations.pointPlusPoint(point1, kPb);

        Point expected = Point.of(301, 734);
        assertEquals(expected, found);
    }
}
