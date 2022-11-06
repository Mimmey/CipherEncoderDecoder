package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PointPair {

    private Point point1;
    private Point point2;

    public static PointPair of(Point p1, Point p2) {
        return new PointPair(p1, p2);
    }

    @Override
    public String toString() {
        return "{" + this.point1.toString() + "," + this.point2.toString() + "}";
    }
}
