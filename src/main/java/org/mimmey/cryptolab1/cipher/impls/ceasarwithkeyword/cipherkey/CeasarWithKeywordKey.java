package org.mimmey.cryptolab1.cipher.impls.ceasarwithkeyword.cipherkey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mimmey.cryptolab1.cipher.interfaces.CipherKey;

@Getter
@AllArgsConstructor
public class CeasarWithKeywordKey implements CipherKey {
    private int shift;
    private String keyString;
}
