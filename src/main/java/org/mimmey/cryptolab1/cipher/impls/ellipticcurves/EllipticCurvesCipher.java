package org.mimmey.cryptolab1.cipher.impls.ellipticcurves;

import lombok.NoArgsConstructor;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.cipherkey.EllipticCurvesKey;
import org.mimmey.cryptolab1.cipher.interfaces.Cipher;

@NoArgsConstructor
public class EllipticCurvesCipher implements Cipher<EllipticCurvesKey> {

    @Override
    public String encode(String text, EllipticCurvesKey cipherKey) {
        return null;
    }

    @Override
    public String decode(String text, EllipticCurvesKey cipherKey) {
        return null;
    }
}
