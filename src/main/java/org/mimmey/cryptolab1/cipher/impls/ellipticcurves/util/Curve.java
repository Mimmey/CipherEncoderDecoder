package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Curve {

    private static final String FORMULA = "x^3 + ax + b";

    private int modulo;
    private Point point;
    private int a;
    private int b;

    public static Curve of(int modulo, Point point, int a, int b) {
        return new Curve(modulo, point, a, b);
    }
}
