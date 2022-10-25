package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Curve {
    int modulo;
    Point point;

    public static Curve of(int modulo, Point point) {
        return new Curve(modulo, point);
    }
}
