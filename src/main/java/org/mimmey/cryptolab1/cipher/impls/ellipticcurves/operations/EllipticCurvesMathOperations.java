package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.operations;

import lombok.AllArgsConstructor;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Curve;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Fraction;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Point;

@AllArgsConstructor
public class EllipticCurvesMathOperations {

    private Curve curve;

    public Point pointSelfMultiplication(Point p, int times) {
        Point result = Point.of(p.getX(), p.getY());

        for (int i = 1; i < Math.abs(times); i++) {
            result = pointPlusPoint(result, p);
        }

        if (times < 0) {
            result.setY(-1 * result.getY());
        }

        return result;
    }

    public Point pointPlusSelf(Point p1) {
        return pointPlusPoint(p1, p1);
    }

    public Point pointPlusPoint(Point p1, Point p2) {
        int lambda = p1.equals(p2) ? findLambdaSelf(p1) : findLambdaOther(p1, p2);

        int xResult = findModuloEqual(
                Fraction.of(lambda * lambda - p1.getX() - p2.getY(), 1),
                curve.getModulo());

        int yResult = findModuloEqual(
                Fraction.of(lambda * (p1.getX() - xResult) - p1.getY(), 1),
                curve.getModulo());

        return Point.of(xResult, yResult);
    }


    private int findLambdaOther(Point p1, Point p2) {
        return findModuloEqual(
                Fraction.of((p2.getY() - p1.getY()), (p2.getX() - p1.getX())),
                curve.getModulo());
    }

    private int findLambdaSelf(Point p) {
        return findModuloEqual(
                Fraction.of(3 * p.getX() * p.getX() + curve.getPoint().getX(), 2 * p.getY()),
                curve.getModulo());
    }

    private int findModuloEqual(Fraction arg, int modulo) {
        int numeratorModulo = Math.floorMod(arg.getNumerator(), modulo);
        int denominatorModulo = findReverseModulo(arg.getDenominator(), modulo);
        return Math.floorMod(numeratorModulo * denominatorModulo, modulo);
    }

    private int findReverseModulo(int denominator, int modulo) {
        int absDenominator = Math.floorMod(denominator, modulo);

        for (int i = 0; i < modulo - 1; i++) {
            if (absDenominator * i % modulo == 1) {
                return i;
            }
        }

        return 0;
    }
}
