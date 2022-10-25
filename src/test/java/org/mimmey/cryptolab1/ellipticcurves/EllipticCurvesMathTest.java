package org.mimmey.cryptolab1.ellipticcurves;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Curve;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.EllipticCurvesMath;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EllipticCurvesMathTest {

    private static Curve curve;

    @BeforeAll
    public static void setCurve() {
        curve = Curve.of(751, Point.of(-1, 1));
    }

    @Test
    public void checkPointSelfMultiplication() {
        Point point = Point.of(0, 1);

        Point found = EllipticCurvesMath.pointSelfMultiplication(point, 3, curve);

        Point expected = Point.of(56, 419);
        assertEquals(expected, found);
    }

    @Test
    public void checkPointPlusSelf() {
        Point point = Point.of(0, 1);

        Point found = EllipticCurvesMath.pointPlusSelf(point, curve);

        Point expected = Point.of(188, 93);
        assertEquals(expected, found);
    }

    @Test
    public void checkPointPlusPoint() {
        Point point1 = Point.of(66, 522);
        Point point2 = Point.of(406, 397);

        Point found = EllipticCurvesMath.pointPlusPoint(
                point1,
                EllipticCurvesMath.pointSelfMultiplication(point2, 3, curve),
                curve
        );

        Point expected = Point.of(301, 734);
        assertEquals(expected, found);
    }
}
