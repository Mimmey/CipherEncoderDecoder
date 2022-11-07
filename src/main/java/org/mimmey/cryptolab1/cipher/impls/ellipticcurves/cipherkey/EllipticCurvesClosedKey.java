package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.cipherkey;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EllipticCurvesClosedKey extends EllipticCurvesKey {
    private final int secretNumber;
}
