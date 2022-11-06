package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.operations;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.consts.EllipticCurvesConsts;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Point;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.PointPair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EllipticCurvesLowLevelOperations {

    public static List<Integer> findAllNumbersInString(String str) {
        List<Integer> allNumbers = new ArrayList<>();
        Matcher matcher = EllipticCurvesConsts.numberPattern.matcher(str);

        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                allNumbers.add(Integer.parseInt(matcher.group(i)));
            }
        }

        return allNumbers;
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
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

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

    public static List<PointPair> pointListStringToPointPairList(String str) {
        List<PointPair> pointPairList = new ArrayList<>();

        List<Integer> numbersInString = findAllNumbersInString(str);

        assert(numbersInString.size() % 4 == 0);

        for (int i = 0; i < numbersInString.size(); i += 4) {
            pointPairList.add(PointPair.of(
                    Point.of(numbersInString.get(i), numbersInString.get(i + 1)),
                    Point.of(numbersInString.get(i + 2), numbersInString.get(i + 3))
            ));
        }

        return pointPairList;
    }
}
