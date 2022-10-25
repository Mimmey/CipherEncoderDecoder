package org.mimmey.cryptolab1.cipher.impls.rijndael.paths;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RijndaelPaths {
    ECB_ENCODE_LOG("src/main/resources/textfiles/rijndael/log/ecb-encode-log.txt"),
    ECB_DECODE_LOG("src/main/resources/textfiles/rijndael/log/ecb-decode-log.txt"),
    OFB_ENCODE_LOG("src/main/resources/textfiles/rijndael/log/ofb-encode-log.txt"),
    OFB_DECODE_LOG("src/main/resources/textfiles/rijndael/log/ofb-decode-log.txt"),
    CBC_ENCODE_LOG("src/main/resources/textfiles/rijndael/log/cbc-encode-log.txt"),
    CBC_DECODE_LOG("src/main/resources/textfiles/rijndael/log/cbc-decode-log.txt"),

    ECB_ENCODE_INPUT("src/main/resources/textfiles/rijndael/ecb/encode/input.txt"),
    ECB_ENCODE_OUTPUT("src/main/resources/textfiles/rijndael/ecb/encode/outputtxt"),
    ECB_ENCODE_EXPECTED("src/main/resources/textfiles/rijndael/ecb/encode/expected.txt"),

    ECB_DECODE_INPUT("src/main/resources/textfiles/rijndael/ecb/decode/input.txt"),
    ECB_DECODE_OUTPUT("src/main/resources/textfiles/rijndael/ecb/decode/output.txt"),
    ECB_DECODE_EXPECTED("src/main/resources/textfiles/rijndael/ecb/decode/expected.txt"),

    OFB_ENCODE_INPUT("src/main/resources/textfiles/rijndael/ofb/encode/input.txt"),
    OFB_ENCODE_OUTPUT("src/main/resources/textfiles/rijndael/ofb/encode/output.txt"),
    OFB_ENCODE_EXPECTED("src/main/resources/textfiles/rijndael/ofb/encode/expected.txt"),

    OFB_DECODE_INPUT("src/main/resources/textfiles/rijndael/ofb/decode/input.txt"),
    OFB_DECODE_OUTPUT("src/main/resources/textfiles/rijndael/ofb/decode/output.txt"),
    OFB_DECODE_EXPECTED("src/main/resources/textfiles/rijndael/ofb/decode/expected.txt"),

    CBC_ENCODE_INPUT("src/main/resources/textfiles/rijndael/cbc/encode/input.txt"),
    CBC_ENCODE_OUTPUT("src/main/resources/textfiles/rijndael/cbc/encode/output.txt"),
    CBC_ENCODE_EXPECTED("src/main/resources/textfiles/rijndael/cbc/encode/expected.txt"),

    CBC_DECODE_INPUT("src/main/resources/textfiles/rijndael/cbc/decode/input.txt"),
    CBC_DECODE_OUTPUT("src/main/resources/textfiles/rijndael/cbc/decode/output.txt"),
    CBC_DECODE_EXPECTED("src/main/resources/textfiles/rijndael/cbc/decode/expected.txt");

    private final String path;
}
