package org.mimmey.cryptolab1.cipher;

import org.mimmey.cryptolab1.cipher.ciphercode.CipherKey;

// Интерфейс, описывающий функционал любого шифра (шифрация + дешифрация)
public interface Cipher<T extends CipherKey> {
    String encode(String text, T cipherKey);

    String decode(String text, T cipherKey);
}
