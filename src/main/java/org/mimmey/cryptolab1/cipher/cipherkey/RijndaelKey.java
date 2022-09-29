package org.mimmey.cryptolab1.cipher.cipherkey;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RijndaelKey implements CipherKey {
    public String key;
}
