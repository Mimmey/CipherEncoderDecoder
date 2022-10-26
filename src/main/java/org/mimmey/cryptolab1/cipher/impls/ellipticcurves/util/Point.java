package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Point {

    private int x;
    private int y;

    public static Point of(int x, int y) {
        return new Point(x, y);
    }
}
