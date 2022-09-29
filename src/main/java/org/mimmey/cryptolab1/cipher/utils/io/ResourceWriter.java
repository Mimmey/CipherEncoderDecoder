package org.mimmey.cryptolab1.cipher.utils.io;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

// Класс, записывающий текст в файл
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceWriter {

    public static void writeToFile(String text, String path) {
        try (FileOutputStream outputStream = new FileOutputStream(Path.of(path).toFile(), true);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            outputStreamWriter.write(text);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
