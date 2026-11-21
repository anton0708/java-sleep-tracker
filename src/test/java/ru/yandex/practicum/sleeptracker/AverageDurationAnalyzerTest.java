package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AverageDurationAnalyzerTest {

    private final AverageDurationAnalyzer analyzer = new AverageDurationAnalyzer();

    @Test
    void shouldReturnZeroForEmptyList() {
        assertEquals(0.0, analyzer.analyze(List.of()), 0.001);
    }

    @Test
    void shouldReturnAverageDuration() {
        SleepSession s1 = new SleepSession(
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 2, 6, 0),   // 480
                SleepQuality.GOOD);
        SleepSession s2 = new SleepSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 7, 0),   // 480
                SleepQuality.NORMAL);
        SleepSession s3 = new SleepSession(
                LocalDateTime.of(2025, 10, 3, 14, 0),
                LocalDateTime.of(2025, 10, 3, 16, 0),  // 120
                SleepQuality.BAD);

        assertEquals(360.0, analyzer.analyze(List.of(s1, s2, s3)), 0.001);
    }
}