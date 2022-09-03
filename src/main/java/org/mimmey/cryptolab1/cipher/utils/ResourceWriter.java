package org.mimmey.cryptolab1.cipher.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceWriter {
    public static void writeToFile(String text, String path) {
        try (FileOutputStream outputStream = new FileOutputStream(Path.of("src/main/resources/" + path).toFile())) {
            byte[] strToBytes = text.getBytes();
            outputStream.write(strToBytes);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
