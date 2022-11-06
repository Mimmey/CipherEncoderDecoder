package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.operations.EllipticCurvesLowLevelOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Point {

    private int x;
    private int y;

    public static Point of(int x, int y) {
        return new Point(x, y);
    }

    public static Point toPoint(String str) {
        List<Integer> allNumbers = EllipticCurvesLowLevelOperations.findAllNumbersInString(str);

        return Point.of(allNumbers.get(0), allNumbers.get(1));
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
