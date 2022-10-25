package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Point {
    int x;
    int y;

    public static Point of(int x, int y) {
        return new Point(x, y);
    }
}
