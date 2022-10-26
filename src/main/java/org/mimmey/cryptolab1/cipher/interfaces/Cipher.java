package org.mimmey.cryptolab1.cipher.interfaces;

// Интерфейс, описывающий функционал любого шифра (шифрация + дешифрация)
public interface Cipher<T, S, K extends CipherKey, M extends CipherKey> {
    String encode(T text, K openCipherKey);

    String decode(S cipherText, M closedCipherKey);
}
