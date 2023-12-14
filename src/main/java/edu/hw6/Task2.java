package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Task2 {
    private Task2() {
    }

    static void cloneFile(Path path) {
        String fileFullName = path.getFileName().toString();
        int dotIndex = fileFullName.lastIndexOf('.');
        String fileName = dotIndex == -1 ? fileFullName : fileFullName.substring(0, dotIndex);
        String extension = dotIndex == -1 ? "" : fileFullName.substring(dotIndex); //with dot
        Path directory = path.getParent();
        Pattern copyNamePattern =
            Pattern.compile(String.format("^%s — копия( \\([1-9][\\d*]*\\))?%s$", fileName, extension));

        try (var filesStream = Files.list(directory)) {
            Set<String> filesFromDir = filesStream.filter(Files::isRegularFile)
                .map(file -> file.getFileName().toString())
                .filter(fullName -> copyNamePattern.matcher(fullName).matches())
                .collect(Collectors.toSet());

            String newFileName = fileName + " — копия" + extension;

            if (!filesFromDir.contains(newFileName)) {
                Files.copy(path, Paths.get(directory.toString(), newFileName));
            } else {
                int numberCopy = 2;
                do {
                    newFileName = String.format("%s — копия (%d)%s", fileName, numberCopy++, extension);
                } while (filesFromDir.contains(newFileName));
                Files.copy(path, Paths.get(directory.toString(), newFileName));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
