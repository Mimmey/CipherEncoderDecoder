package org.mimmey.cryptolab1.cipher.ciphercode;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Реализация интерфейса "CiphferKey" для метода
// Цезаря с ключевым словом
@Getter
@AllArgsConstructor
public class CeasarWithKeywordKey implements CipherKey {
    private int shift;
    private String keyString;
}
