package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.cipherkey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Point;

import java.util.List;

@Getter
@AllArgsConstructor
public class EllipticCurvesOpenKey extends EllipticCurvesKey {
    private Point openKey;
    private List<Integer> randomNumberList;
}
