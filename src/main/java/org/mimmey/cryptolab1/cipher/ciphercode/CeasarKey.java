package org.mimmey.cryptolab1.cipher.ciphercode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CeasarKey extends CipherKey {
    private int shift;
    private String keyString;
}
