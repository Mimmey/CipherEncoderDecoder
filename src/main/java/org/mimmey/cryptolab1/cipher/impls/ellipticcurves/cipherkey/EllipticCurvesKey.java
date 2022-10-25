package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.cipherkey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mimmey.cryptolab1.cipher.interfaces.CipherKey;

@Getter
@AllArgsConstructor
public class EllipticCurvesKey implements CipherKey {
    private int shift;
    private String keyString;
}
