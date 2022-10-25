package org.mimmey.cryptolab1.cipher.impls.ceasarwithkeyword.paths;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CeasarWithKeywordPaths {
    ENCODE_INPUT("src/main/resources/textfiles/ceasarwithkeyword/encode/input.txt"),
    ENCODE_OUTPUT("src/main/resources/textfiles/ceasarwithkeyword/encode/output.txt"),
    ENCODE_EXPECTED("src/main/resources/textfiles/ceasarwithkeyword/encode/expected.txt"),

    DECODE_INPUT("src/main/resources/textfiles/ceasarwithkeyword/decode/input.txt"),
    DECODE_OUTPUT("src/main/resources/textfiles/ceasarwithkeyword/decode/output.txt"),
    DECODE_EXPECTED("src/main/resources/textfiles/ceasarwithkeyword/decode/expected.txt");

    private final String path;
}