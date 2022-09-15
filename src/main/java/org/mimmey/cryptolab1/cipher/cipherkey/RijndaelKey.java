package org.mimmey.cryptolab1.cipher.cipherkey;

import org.mimmey.cryptolab1.cipher.cipherkey.units.Sbox;

import java.util.List;

public class RijndaelKey implements CipherKey {
    List<Sbox> sboxList;
}
