package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SleepLogParser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public static List<SleepSession> readLog(String filePath) throws IOException {

        return Files.lines(Paths.get(filePath))
                .filter(line -> !line.isBlank())
                .map(SleepLogParser::parseLine)
                .toList();
    }

    private static SleepSession parseLine(String line) {
        String[] parts = line.split(";");

        LocalDateTime start = LocalDateTime.parse(parts[0].trim(), FORMATTER);
        LocalDateTime end = LocalDateTime.parse(parts[1].trim(), FORMATTER);
        SleepQuality quality = SleepQuality.valueOf(parts[2].trim().toUpperCase());

        return new SleepSession(start, end, quality);
    }
}
