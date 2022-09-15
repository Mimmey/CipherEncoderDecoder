package org.mimmey.cryptolab1.cipher.cipherkey;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Реализация интерфейса "CiphferKey" для шифрования
// по методу Цезаря с ключевым словом
@Getter
@AllArgsConstructor
public class CeasarWithKeywordKey implements CipherKey {
    private int shift;
    private String keyString;
}
