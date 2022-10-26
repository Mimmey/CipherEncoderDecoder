package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Fraction {

    private int numerator;
    private int denominator;

    public static Fraction of(int numerator, int denominator) {
        return new Fraction(numerator, denominator);
    }
}
