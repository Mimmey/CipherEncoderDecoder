package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.operations;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.consts.EllipticCurvesConsts;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Point;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.PointPair;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EllipticCurvesLowLevelOperations {

    public static List<Point> stringToPointList(String s) {
        List<Point> pointList = new ArrayList<>();

        for (char c : s.toCharArray()) {
            pointList.add(charToPoint(c));
        }

        return pointList;
    }

    public static Point charToPoint(char c) {
        return EllipticCurvesConsts.ALPHABET.get(c);
    }

    public static String pointListToString(List<Point> pointList) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Point point : pointList) {
            stringBuilder.append(pointToChar(point));
        }

        return stringBuilder.toString();
    }

    public static Character pointToChar(Point point) {
        return EllipticCurvesConsts.ALPHABET
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(point))
                .findFirst()
                .orElseThrow()
                .getKey();
    }

    public static String pointPairListToString(List<PointPair> pointPairList) {
        StringBuilder stringBuilder = new StringBuilder();

        for (PointPair pointPair : pointPairList) {
            stringBuilder.append(pointPair.toString());
        }

        return stringBuilder.toString();
    }
}
