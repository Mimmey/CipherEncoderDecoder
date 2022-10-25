package org.mimmey.cryptolab1.cipher.impls.rijndael.cipherkey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mimmey.cryptolab1.cipher.interfaces.CipherKey;

@Getter
@AllArgsConstructor
public class RijndaelKey implements CipherKey {
    public String key;
}
