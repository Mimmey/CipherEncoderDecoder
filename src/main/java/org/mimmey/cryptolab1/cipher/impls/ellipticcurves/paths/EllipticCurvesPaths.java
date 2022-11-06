package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.paths;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EllipticCurvesPaths {
    ENCODE_INPUT("src/main/resources/textfiles/ellipticcurves/encode/input.txt"),
    ENCODE_OUTPUT("src/main/resources/textfiles/ellipticcurves/encode/output.txt"),
    ENCODE_EXPECTED("src/main/resources/textfiles/ellipticcurves/encode/expected.txt"),

    DECODE_INPUT("src/main/resources/textfiles/ellipticcurves/decode/input.txt"),
    DECODE_OUTPUT("src/main/resources/textfiles/ellipticcurves/decode/output.txt"),
    DECODE_EXPECTED("src/main/resources/textfiles/ellipticcurves/decode/expected.txt");

    private final String path;
}