package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EllipticCurvesMath {

    public static Point pointSelfMultiplication(Point p, int times, Curve curve) {
        Point result = Point.of(p.x, p.y);

        for (int i = 1; i < times; i++) {
            result = pointPlusPoint(result, p, curve);
        }

        return result;
    }

    public static Point pointPlusSelf(Point p1, Curve curve) {
        return pointPlusPoint(p1, p1, curve);
    }

    public static Point pointPlusPoint(Point p1, Point p2, Curve curve) {
        int lambda = p1.equals(p2) ? findLambdaSelf(p1, curve) : findLambdaOther(p1, p2, curve);

        int xResult = findModuloEqual(
                Fraction.of(lambda * lambda - p1.x - p2.x, 1),
                curve.modulo);

        int yResult = findModuloEqual(
                Fraction.of(lambda * (p1.x - xResult) - p1.y, 1),
                curve.modulo);

        return Point.of(xResult, yResult);
    }


    private static int findLambdaOther(Point p1, Point p2, Curve curve) {
        return findModuloEqual(
                Fraction.of((p2.y - p1.y), (p2.x - p1.x)),
                curve.modulo);
    }

    private static int findLambdaSelf(Point p, Curve curve) {
        return findModuloEqual(
                Fraction.of(3 * p.x * p.x + curve.point.x, 2 * p.y),
                curve.modulo);
    }

    private static int findModuloEqual(Fraction arg, int modulo) {
        int numeratorModulo = Math.floorMod(arg.numerator, modulo);
        int denominatorModulo = findReverseModulo(arg.denominator, modulo);
        return Math.floorMod(numeratorModulo * denominatorModulo, modulo);
    }

    private static int findReverseModulo(int denominator, int modulo) {
        int absDenominator = Math.floorMod(denominator, modulo);

        for (int i = 0; i < modulo - 1; i++) {
            if (absDenominator * i % modulo == 1) {
                return i;
            }
        }

        return 0;
    }
}
